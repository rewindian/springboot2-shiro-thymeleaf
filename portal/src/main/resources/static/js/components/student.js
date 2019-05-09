var stuComponents = (function () {
    return {
        template: "#stu-template",
        props: {
            title: String
        },
        data: function () {
            return {
                message: '学生列表',
                text: ''
            }
        },
        methods: {
            pressKey: Throttle(function () {
                console.log(this.text);
            })
        }
    };
})();