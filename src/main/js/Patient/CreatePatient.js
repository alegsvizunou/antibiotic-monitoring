'use strict';

const React = require('react');

class CreatePatient extends React.Component{

    constructor(props) {
        super(props);
        this.create = this.create.bind(this);
        this.close = this.close.bind(this);
        this.changeShow = this.changeShow.bind(this);
        this.renderNull = this.renderNull.bind(this);
        this.renderUp = this.renderUp.bind(this);
        this.state = { showOnCreate: false};

    }

    changeShow(){
        this.setState({
            showOnCreate: !this.state.showOnCreate
        });
    }

    create(e) {
        e.preventDefault();
        let firstname = this.refs.update_firstname2.value;
        let lastname = this.refs.update_lastname2.value;
        let surname = this.refs.update_surname2.value;
        let birthyear = this.refs.update_birthyear2.value;

        class Patient {
            constructor(firstname, lastname, surname, birthyear) {
                this.firstname=firstname;
                this.lastname=lastname;
                this.surname=surname;
                this.birthyear=birthyear;
            }
        }


        this.props.createPat(new Patient(firstname, lastname, surname, birthyear));
        this.changeShow();
    }

    close(){
        this.changeShow();
    }

    renderUp() {

        return (

            <div className="col-6">
                <form ref="myForm">
                    <input type="text" ref="update_surname2" placeholder="Фамилия" className="form-control"/>
                    <input type="text" ref="update_firstname2" placeholder="Имя" className="form-control"/>
                    <input type="text" ref="update_lastname2" placeholder="Отчество" className="form-control"/>

                    <input type="text" ref="update_birthyear2" placeholder="Год рождения" className="form-control"/>
                    <button onClick={this.create} className="btn btn-outline-dark">Сохранить</button>
                    <button onClick={this.close} className="btn btn-outline-dark">Закрыть</button>
                </form>
            </div>
        )

    }

    renderNull() {
        return (
            <div >
                <button onClick={this.changeShow} className="btn btn-outline-dark">Создать пациента</button>
            </div>
        )
    }

    render() {
        if (this.state.showOnCreate) {
            return this.renderUp();
        } else {
            return this.renderNull();
        }
    }

}

export default CreatePatient;