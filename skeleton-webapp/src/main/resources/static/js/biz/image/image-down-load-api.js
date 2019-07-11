let api = {

    /**
     * show name
     * @returns {string}
     */
    showName(){
       return '-- 下载 --';
    },

    /**
     * 获取下载api地址
     * @param downLoadUrl
     * @param storePath
     * @returns {string}
     */
    getDownLoad(downLoadUrl,storePath){
        return '/down/save?downLoadUrl=' + downLoadUrl + '&storePath=' + storePath
    },

    /**
     * post
     * @returns {string}
     */
    getPostDownLoad(){
        return '/down/save';
    }
}