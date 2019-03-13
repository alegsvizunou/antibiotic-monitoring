
var path = require('path');
var node_dir = __dirname + '/node_modules';

module.exports = {
    entry: {main :'./src/main/js/app.js'},
    devtool: 'source-map',
    cache: true,
    output: {
        path: path.resolve(__dirname,'./src/main/resources/static/built/'),
        filename: 'bundle.js',
        publicPath: "built/"
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /(node_modules)/,
                use: ['babel-loader']
            }
        ]
    },
    resolve: {
        extensions: ['*', '.js', '.jsx']
    }
};