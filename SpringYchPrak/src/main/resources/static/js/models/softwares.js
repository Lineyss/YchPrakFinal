let publishers_api = 'http://localhost:8000/api/publishers'

let publishers;

await fetch(publishers_api)
    .then((responce) => responce.json())
    .then((data) => publishers = data.currentItems);

export function createData(url)
{
    let return_string = `
        <h2>Создать роль</h2>
        <form method='POST' action='${url}' id='form_create'>
            <p>
                <input placeholder='Название' name='Title' required>
            </p>
            <p>
                <input placeholder='Описание' name='Description' required>
            </p>
            <p>
                <select name='IDPublisher'>
                    ${
                        publishers.map(publisher => 
                            `<option value="${publisher.id}"> ${publisher.title} </option>`
                        )
                        .join('')
                    }
                    <option></option>
                </select>
            </p>
            <p>
                <input type='submit' value='Создать'/>
            </p>
        </form>
    `;
    return return_string;
}

export function viewData(models) {
   return models.map(user =>
        `
        <form method='PUT' class="items">
            <input type='checkbox'>
            <p>${user.id}</p>
            <input placeholder='Логин' value=${user.title} name='title' required/>
            <input placeholder='Пароль' value=${user.description} name='description' required/>
            <select name='IDPublisher'>
                ${
                    publishers.map(publisher =>
                        `<option value="${publisher.id}" ${publisher.id == user?.publisher?.id ? 'selected' : '' }> ${publisher.title} </option>`
                    )
                    .join('')
                }
                <option></option>
            </select>
            <button type='submit'>
                Обновить
            </button>
        </form>
        `
        )
    .join('');
}