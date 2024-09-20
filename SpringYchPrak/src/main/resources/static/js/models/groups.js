export function createData(url)
{
    let return_string = `
        <h2>Создать роль</h2>
        <form method='POST' action='${url}' id='form_create'>
            <p>
                <input placeholder='Название' name='Hostname' required>
            </p>
            <p>
                <input type='submit' value='Создать'/>
            </p>
        </form>
    `;
    return return_string;
}

export function viewData(models) {
   return models.map(groups =>
        `
        <form method='PUT' class="items">
            <input type='checkbox'>
            <p>${groups.id}</p>
            <input placeholder='Название' value=${groups.title} name='title' required/>
            <p>Колличество подключенных хостов: ${groups.numberConnected}</p>
            <button type='submit'>
                Обновить
            </button>
        </form>
        `
        )
    .join('');
}