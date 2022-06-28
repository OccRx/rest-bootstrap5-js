let navbarUserInfo = document.getElementById('authenticationUser')


let rol = '';
function showNavbar (){
    info().then(user => {
        let navbarUserData = ''
        let roles = user.roles
        // rol = ''
        user.roles.forEach(function (role) {
            rol += role["roleName"].substring(5) + ' '
        })
        navbarUserData += ` <b><span>${user.email}</span></b>
                            <span>with roles:</span>
                             <span >${rol}</span>`
        navbarUserInfo.innerHTML = navbarUserData;
    })
}

