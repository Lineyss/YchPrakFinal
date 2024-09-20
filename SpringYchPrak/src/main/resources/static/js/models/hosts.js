let groups_api = 'http://localhost:8000/api/groups'

let groups;

await fetch(groups_api)
    .then((responce) => responce.json())
    .then((data) => groups = data.currentItems);

export function createData(url)
{
    let return_string = `
        <h2>Создать роль</h2>
        <form method='POST' action='${url}' id='form_create'>
            <p>
                <input placeholder='Название' name='Hostname' required>
            </p>
            <p>
                <select name='IDGroup'>
                    ${
                        groups.map(groups =>
                            `<option value="${groups.id}"> ${groups.title} </option>`
                        )
                        .join('')
                    }
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
   return models.map(host =>
        `
        <form method='PUT' class="items">
            <input type='checkbox'>
            <p>${host.id}</p>
            <input placeholder='Логин' value=${host.hostname} name='Hostname' required/>
            <select name='IDGroup'>
                ${
                    groups.map(group =>
                        `<option value="${group.id}" ${group.id == host?.group?.id ? 'selected' : '' }> ${group.title} </option>`
                    )
                    .join('')
                }
            </select>
            <button type='submit'>
                Обновить
            </button>
        </form>
        `
        )
    .join('');
}