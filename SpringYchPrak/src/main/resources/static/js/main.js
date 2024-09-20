import { sendRequest } from "./sendHttp.js";

let deleted_items = [];

let viewData;
let createData;

const view_model = document.querySelector(".view-model");
const create_model = document.querySelector(".create-model");

let api_url = document.getElementById('api-url').innerHTML;
let get_js_path = document.getElementById('js_path').innerHTML;

const initializeModule = async () => {
    try {
        const module = await import(get_js_path);
        viewData = module.viewData;
        createData = module.createData;
    } catch (error) {
        console.error("Ошибка при импортировании:", error);
    }
};

const backPage = () =>
{
    const current_page = Number(document.getElementById("current_page").innerHTML);
    getAll(current_page - 1);
} 

const nextPage = () =>
{
    const current_page = Number(document.getElementById("current_page").innerHTML);
    getAll(current_page + 1);
} 

const getAll = (page) =>
{
    let url = `${api_url}s?page=${page}`;
    console.log(url);
    sendRequest(url, null, "GET", false, function(){
        let data = this.response ? JSON.parse(this.response) : this.response;

        if(this.status != 200)
        {
            alert(data.errotText);
        }
        else if (this.status == 200)
        {
            let models = data.currentItems;
            console.log(models);
            view_model.innerHTML = `
                <div class="view-model-header">
                    <button id="delete" disabled>
                        Удалить
                    </button>
                </div>
                <div class="view-model-content">

                    ${viewData(models)}
                    
                </div>
                <div class="pagination">
                    ${ 
                        data.currentPage > 1 ? 
                        '<p id="back" class="pagination_element"> Назад </p>'
                        : ""
                    }

                    <p id="current_page">
                        ${data.currentPage}
                    </p>

                    ${ 
                        data.currentPage < data.totalNumberPages 
                        ? '<p id="next" class="pagination_element"> Вперед </p>' 
                        : ""
                    }

                </div>
            `;

            create_model.innerHTML = createData(api_url);

            let buttonDelete = document.getElementById("delete");

            document.querySelectorAll(".items")?.forEach(item => {
                item.querySelector("input[type=checkbox]").addEventListener("click", function() 
                {
                    let id = item.querySelector('p').innerHTML;

                    if(this.checked)
                    {
                        deleted_items.push(id);
                    }
                    else
                    {
                        let index = deleted_items.indexOf(id);
                        if(index > -1) deleted_items.splice(index, 1);
                    }

                    if(deleted_items.length == 0)
                    {
                        buttonDelete.disabled = true;
                    }
                    else
                    {

                        buttonDelete.disabled = false;
                    }
                });
                
                item.addEventListener("submit", function(event){
                    event.preventDefault();
                    let id = this.querySelector('p').innerHTML;
                    let method = this.getAttribute("method");
                    let url = `${api_url}/${id}`;
                    let data = new FormData(this);
                    sendRequest(url, data, method, false, null, null, function(){
                        location.reload();
                    });
                });
            });


            document.getElementById("next")?.addEventListener("click", nextPage)
            document.getElementById("next")?.addEventListener("click", backPage)
            buttonDelete?.addEventListener("click", function(){
                for(let i = 0; i < deleted_items.length; i++)
                {
                    let url = `${api_url}/${deleted_items[i]}`
                    
                    sendRequest(url, null, "DELETE");
                }

                location.reload();
            });

            document.getElementById('form_create')?.addEventListener("submit", function(event) {
                event.preventDefault();

                let form = new FormData(this);

                sendRequest(api_url, form, "POST", false, null, null, function() {
                    location.reload();
                });
            });
        }
    });
}


await initializeModule();
getAll(1);