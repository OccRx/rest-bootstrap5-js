document.addEventListener("DOMContentLoaded", function (event) {
    event.currentTarget
    showAllUsers().catch()
    findAllRoles().catch()
    showNavbar()
});

let table = document.getElementById('tableUser')


async function showAllUsers() {
    table.innerHTML = ''
    const usersResponse = await usersService.findAll();
    const usersJson = usersResponse.json();
    usersJson
        .then(usersData => {
            let userData = ''
            usersData.map(user => {
                let roles = user.roles
                let rol = ''
                roles.forEach(function (role) {
                    rol += role["roleName"].substring(5) + ' '
                })
                userData += `
                                     <tr>
                                        <td>${user.id}</td>
                                        <td>${user.name}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.age}</td>
                                        <td>${user.email}</td>
                                        <td>${rol}</td>
                                        <td>
                                            <button type="button" class="btn btn-info text-white" 
                                              data-bs-target="#defaultModal" 
                                              data-bs-toggle = "modal" data-bs-id="${user.id}"
                                            >Edit</button>
                                        </td>
                                        <td>
                                        <button type="button" class="btn btn-danger"
                                         data-bs-toggle="modal" 
                                            data-bs-target="#deleteModal" data-bs-id="${user.id}">Delete</button>
                                        </td>
                                     </tr>
                                        `
            })
            table.innerHTML = userData;
        });

}

const submitEdit = document.getElementById("formUpdate");
submitEdit.addEventListener("submit", handleFormSubmit);

async function handleFormSubmit(event) {
    event.preventDefault();
    const form = document.getElementById('formUpdate')
    const formData = new FormData(form);
    const select = document.getElementById('selectRole')
    const roles = []
    if (select.multiple) {
        for (let i = 0; i < select.options.length; i++) {
            if (select.options[i].selected) {
                let rol = {
                    id: select.options[i].value,
                    roleName: 'ROLE_' + select.options[i].text
                }
                roles.push(rol)
            }
        }
    }
    const plainFormData = Object.fromEntries(formData.entries())
    plainFormData['roles'] = roles
    await usersService.update2(plainFormData)
    let modalInstance = bootstrap.Modal.getInstance(modal)
    modalInstance.hide();
    showAllUsers().catch()
}


let modal = document.getElementById('defaultModal')
modal.addEventListener('show.bs.modal', async function (event) {
    document.querySelector('#selectRole').innerHTML = ''
    let button = event.relatedTarget
    let userId = button.getAttribute('data-bs-id')
    const userResponse = await usersService.findById(userId);
    const userJson = userResponse.json();
    const rolesResponse = await rolesService.findAll();
    const rolesJson = rolesResponse.json();
    let modalBodyInput = modal.querySelectorAll('.modal-body input')
    userJson.then(user => {
        modalBodyInput[0].value = user.id
        modalBodyInput[1].value = user.name
        modalBodyInput[2].value = user.lastName
        modalBodyInput[3].value = user.age
        modalBodyInput[4].value = user.email
        modalBodyInput[5].value = user.password
        rolesJson.then(roles => {
            roles.forEach(function (role) {
                let selectRole = modal.querySelector('.modal-body select');

                if (user.roles.some(o => o.id === role.id)) {
                    selectRole.append(new Option(role["roleName"].substring(5), role.id, false, true))
                } else {
                    selectRole.append(new Option(role["roleName"].substring(5), role.id))
                }
            })
        })
    })

})

const submitDelete = document.getElementById("deleteForm");
submitDelete.addEventListener("submit", handleFormDelete);

async function handleFormDelete(event) {
    event.preventDefault()
    const fromDelete = document.getElementById('deleteForm')
    const id = fromDelete.querySelector('#delID').value
    await usersService.delete(id)
    let modalInstance = bootstrap.Modal.getInstance(modalDelete)
    modalInstance.hide();
    showAllUsers().catch()
}

let modalDelete = document.getElementById('deleteModal')
modalDelete.addEventListener('show.bs.modal', async function (event) {
    document.querySelector('#deleteselectRole').innerHTML = ''
    let button = event.relatedTarget
    let userId = button.getAttribute('data-bs-id')
    const userResponse = await usersService.findById(userId);
    const userJson = userResponse.json();
    const rolesResponse = await rolesService.findAll();
    const rolesJson = rolesResponse.json();
    let modalBodyInput = modalDelete.querySelectorAll('.modal-body input')
    userJson.then(user => {
        modalBodyInput[0].value = user.id
        modalBodyInput[1].value = user.name
        modalBodyInput[2].value = user.lastName
        modalBodyInput[3].value = user.age
        modalBodyInput[4].value = user.email
        modalBodyInput[5].value = user.password
        rolesJson.then(roles => {
            roles.forEach(function (role) {
                let selectRole = modalDelete.querySelector('.modal-body select');
                if (user.roles.some(o => o.id === role.id)) {
                    selectRole.append(new Option(role["roleName"].substring(5), role.id, false, true))
                } else {
                    selectRole.append(new Option(role["roleName"].substring(5), role.id))
                }
            })
        })
    })
})

const newUserFrom = document.getElementById('newUserFrom')

async function findAllRoles() {
    const rolesResponse = await rolesService.findAll();
    const rolesJson = rolesResponse.json();
    rolesJson.then(roles => {
        roles.forEach(function (role) {
            const selectRole = newUserFrom.querySelector('.card-body select');
            selectRole.append(new Option(role["roleName"].substring(5), role.id))
        })
    })
}

const submitNewUser = document.getElementById("newUserFrom");
submitNewUser.addEventListener('submit', handleFormNewUser);

async function handleFormNewUser(event) {
    event.preventDefault();
    const newUserFrom = document.getElementById('newUserFrom')
    const formData1 = new FormData(newUserFrom);
    const select = document.getElementById('selectRoleNew')

    const roless = []
    if (select.multiple) {
        for (let i = 0; i < select.options.length; i++) {
            if (select.options[i].selected) {
                let rol = {
                    id: select.options[i].value,
                    roleName: 'ROLE_' + select.options[i].text
                }
                roless.push(rol)
            }
        }

    }
    const plainFormData = Object.fromEntries(formData1.entries())
    plainFormData['roles'] = roless
    await usersService.add(plainFormData)

    let aActive = document.getElementById('nav-profile')
    let notActive = document.getElementById('nav-home')
    let buttonActive = document.getElementById('nav-profile-tab')
    let buttonNotActive = document.getElementById('nav-home-tab')
    aActive.setAttribute('class', 'tab-pane fade')
    buttonActive.setAttribute('class', 'nav-link')
    notActive.setAttribute('class', 'tab-pane fade active show')
    buttonNotActive.setAttribute('class', 'nav-link active')
    newUserFrom.reset()

    showAllUsers().catch()
}



