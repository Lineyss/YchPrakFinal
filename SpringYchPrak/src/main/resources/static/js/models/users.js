let url_roles = "http://localhost:8000/api/roles";
let roles;

await fetch(url_roles)
    .then((responce) => responce.json())
    .then((module) => {
        roles = module.currentItems;
    });

export function createData(url)
{
    let return_string = `
        <h2>Создать роль</h2>
        <form method='POST' action='${url}' id='form_create'>
            <p>
                <input placeholder='Логин' name='Login' required>
            </p>
            <p>
                <input placeholder='Пароль' name='Password' required>
            </p>
            <p>
                <select name='IDRole'>
                    ${
                        roles.map(role => 
                            `<option value="${role.id}"> ${role.title} </option>`
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
   return models.map(user =>
        `
        <form method='PUT' class="items">
            <input type='checkbox'>
            <p>${user.id}</p>
            <input placeholder='Логин' value=${user.login} name='Login' required/>
            <input placeholder='Пароль' value=${user.password} name='Password' required/>
            <select name='IDRole'>
            ${
                roles.map(role => 
                    `<option value="${role.id}" ${role.id == user?.role?.id ? 'selected' : ''}> ${role.title} </option>`
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