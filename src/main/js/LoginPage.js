const React = require('react');
const ReactDOM = require('react-dom');

class LoginPage extends React.Component {


    render() {

        return (

                <div className="container">
                    <div className="row justify-content-center">
                        <div className="col-4">

                            <form name='f' action='/login' method='POST'>

                                <input type="text" name='username' placeholder="Логин" className="form-control"/>
                                <input type='password' name='password' placeholder="Пароль" className="form-control"/>
                                <button className="btn btn-outline-dark" type="submit" name="submit"> Login </button>

                            </form>

                        </div>
                    </div>
                </div>
        )

    }


}

export default LoginPage;

