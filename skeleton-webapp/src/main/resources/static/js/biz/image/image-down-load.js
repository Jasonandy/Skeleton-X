new Vue({
    el: '#app',
    data() {
        return {
            downLoadUrl: "https://mirrors.tuna.tsinghua.edu.cn/apache/tomcat/tomcat-8/v8.5.43/bin/apache-tomcat-8.5.43.tar.gz",
            storePath: "E:\\"
        }
    },
    methods: {
        /**
         * 下载
         */
        downLoad(){
            let showName = api.showName();
            alert('== downLoad =='+showName);
            console.log(api.getPostDownLoad())
            console.log(api.getDownLoad(this.downLoadUrl,this.storePath));
            // this.$http.post(api.getPostDownLoad()).then(response => {
            //     let data = response.body.data
            //     console.log(data);
            // });

            this.$http.get(api.getDownLoad(this.downLoadUrl,this.storePath)).then(response => {
                let respBody = response.body;
                let data = respBody.data;
                let code = respBody.code;
                let msg = respBody.msg;
                console.log(data,code,msg);
                if (200 == code){
                    alert('恭喜下载成功!');
                }
            })
        }
    }
});
