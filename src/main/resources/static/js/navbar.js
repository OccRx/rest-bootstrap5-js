let navbarUserInfo = document.getElementById('authenticationUser')

function showNavbar (){
    info().then(user => {
        let navbarUserData = ''
        let roles = user.roles
        let rol = ''
        roles.forEach(function (role) {
            rol += role["roleName"].substring(5) + ' '
        })
        navbarUserData += ` <b><span>${user.email}</span></b>
                            <span>with roles:</span>
                             <span >${rol}</span>`
        navbarUserInfo.innerHTML = navbarUserData;
    })
}