// let output = ''
// const ul = document.getElementById('users');
// const url = 'http://localhost:8080/admin/users';
// fetch(url)
//     .then((response) => {
//         return response.json();
//     })
//     .then((usersData) => {
//         usersData.forEach(user => {
//             let roles = user.roles
//             let rol = ''
//             roles.forEach(function(role){
//                 rol += role.roleName.substring(5) + " "
//             })
//             output += `<li>${user.email} ${rol}</li>`;
//         })
//         ul.innerHTML = output;
//     });
// document.ready(showAllUsers())

//
// let tt = document.getElementById('btnEdit')
//
// console.log(tt)

const http = {
    fetch: async function(url, options = {}) {
        return await fetch(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            ...options,
        });
    }
};

const usersService = {
    findAll: async () => {
        return await http.fetch('/api/users');
    },
    add: async (data) => {
        return await http.fetch('/api/users', {
            method: 'POST',
            body: JSON.stringify(data)
        });
    },
    findById: async (id) => {
        return await http.fetch('/api/users/' + id);
    },
    update: async (id, data) => {
        return await http.fetch('/api/users/' + id, {
            method: 'PUT',
            body: JSON.stringify(data)
        });
    },
    delete: async (id) => {
        return await http.fetch('/api/users/' + id, {
            method: 'DELETE'
        });
    },
};


// const api_rl = 'http://localhost:8080/api/users'
//
// let getAllUsers = fetch(api_rl).then(response => {
//     return response.json()
// })

 async function showAllUsers() {

        getAllUsers




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
                                        <td>${user.email}"</td>
                                        <td>${rol}</td>
                                        <td>
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" 
                                        data-bs-target="#editModal1" data-bs-id="${user.id}">modal</button>

                                            <button class="btn btn-info text-white btnEdit" onclick="editModal()"
                                                    type="button" data-bs-id = "${user.id}"
                                            >Edit
                                            </button>
                                        </td>
                                        <td>
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" 
                                        data-bs-target="#exampleModal" data-bs-whatever="@fat">modal2</button>

                                            <button class="btn btn-danger btnDelete" onclick="delModal()" data-bs-id = "${user.id}"
                                            >Delete
                                            </button>
                                        </td>
                                     </tr>
                                        `
            })
            console.log(getAllUsers)
            document.getElementById('tableUser').innerHTML = userData;
        });
}

let exampleModal = document.getElementById('editModal1')
exampleModal.addEventListener('show.bs.modal', function (event) {
    // Button that triggered the modal
    let button = event.relatedTarget
    // Extract info from data-bs-* attributes
    let userId = button.getAttribute('data-bs-id')
    // If necessary, you could initiate an AJAX request here
    // and then do the updating in a callback.
    //
    // Update the modal's content.
    let modalTitle = exampleModal.querySelector('.modal-title')
    let modalBodyInput = exampleModal.querySelector('.modal-body input[name=userId]')
    let modalBodyInput2 = exampleModal.querySelector('.modal-body input[name=userName]')
    console.log(userId)
    console.log(userId.id)

    modalTitle.textContent = 'Это заголовок ' + userId["id"]
    modalBodyInput.value = userId
    modalBodyInput2.value = '5'
})

async function editModal(){
    let editUserModal = new bootstrap.Modal(document.getElementById('editForm'));
    // let modalTitle = editUserModal.querySelector('.modal-title')
    // modalTitle.textContent = 'парам пам пам'
    editUserModal.show(editUserModal)
}

async function delModal(){
    let editUserModal = new bootstrap.Modal(document.getElementById('editForm'));
    editUserModal.show()
}

// $('#exampleModal').on('show.bs.modal', function (event) {
//     var button = $(event.relatedTarget) // Button that triggered the modal
//     var recipient = button.data('whatever') // Extract info from data-* attributes
//     // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
//     // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
//     var modal = $(this)
//     modal.find('.modal-title').text('New message to ' + recipient)
//     modal.find('.modal-body input').val(recipient)
// })
showAllUsers()



