export function createData(url)
{
    let return_string = `
        <h2>Создать роль</h2>
        <form method='POST' action='${url}' id='form_create'>
            <p>
                <input placeholder='Название роли' name='Title' required>
            </p>
            <p>
                <input type='submit' value='Создать'/>
            </p>
        </form>
    `;
    return return_string;
}

export function viewData(models) {
   return models.map(role =>
        `
        <form method='PUT' class="items">
            <input type='checkbox'>
            <p>${role.id}</p>
            <input placeholder='Название роли' value=${role.title} name='Title' required/>
            <button type='submit'>
                Обновить
            </button>
        </form>
        `
        )
    .join('');
}