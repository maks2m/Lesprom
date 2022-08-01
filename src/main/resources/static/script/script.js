const app = {
    data() {
        return {
            users: [],
            editUser: '',
            roles: [],
        }
    },
    mounted() {
        axios
            .get('http://localhost:8080/api/user')
            .then(response => (this.users = response.data));
        axios
            .get('http://localhost:8080/api/role')
            .then(response => (this.roles = response.data));
    },
    methods: {
        save: function (user) {
            console.log(user)
        },
        edit: function (user) {
            this.editUser = user;
        },
        del: function (id, index) {
/*
            axios.delete(
                'http://localhost:8080/api/user/' + id,
                {headers: {
                        Authorization: "Basic ZWxraW46ZWxraW4="
                    },
                    data:{
                    }}
            );
*/
            this.users.splice(index, 1);
        }
    }
}

Vue.createApp(app).mount('#app');