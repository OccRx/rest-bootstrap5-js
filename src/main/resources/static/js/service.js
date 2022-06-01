
const http = {
    fetch: async function (url, options = {}) {
        return await fetch(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            ...options,
        });
    }
};

const rolesService = {
    findAll: async () => {
        return await http.fetch('/api/roles')
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
    authUser: async (data) => {
        return await http.fetch('/api/authUser', {
            method: 'GET',
            body: JSON.stringify(data)
        });
    },
    update2: async (data) => {
        return await http.fetch('/api/users', {
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

const info =
    async function authUser() {
        let userResponse = await usersService.authUser();
        return userResponse.json()
    }
