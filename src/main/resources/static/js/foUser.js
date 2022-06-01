document.addEventListener("DOMContentLoaded", function (event) {
    event.currentTarget
    showUserInfo()
    showNavbar()
});

let tableUserInfo = document.getElementById('tableUserInfo')

function showUserInfo() {
    tableUserInfo.innerHTML = ''
    info().then(user => {
        let userDataTable = ''
        let roles = user.roles
        let rol = ''
        roles.forEach(function (role) {
            rol += role["roleName"].substring(5) + ' '
        })
        userDataTable += ` <tr>
                                        <td>${user.id}</td>
                                        <td>${user.name}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.age}</td>
                                        <td>${user.email}</td>
                                        <td>${rol}</td>
                                     </tr>
                                        `
        tableUserInfo.innerHTML = userDataTable;
    })
}

