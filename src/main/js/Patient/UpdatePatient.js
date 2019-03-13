'use strict';

const React = require('react');

class UpdatePatient extends React.Component {
    constructor(props) {
        super(props);
        this.Update = this.Update.bind(this);
        this.renderUp = this.renderUp.bind(this);
        this.renderNull = this.renderNull.bind(this);

    }

    Update(e) {
        e.preventDefault();
        let firstname = this.refs.update_firstname.value;
        let lastname = this.refs.update_lastname.value;
        let surname = this.refs.update_surname.value;
        let birthyear = this.refs.update_birthyear.value;
        this.props.onSaveUp(firstname, lastname, surname, birthyear, this.props.patientUp);

    }


    renderUp() {

        let fir= this.props.patientUp.firstname;
        let las= this.props.patientUp.lastname;
        let sur= this.props.patientUp.surname;
        let bir= this.props.patientUp.birthyear;

        return (

            <div className="col-6">
                <form ref="myForm2">


                    <input type="text" ref="update_surname" placeholder="Фамилия" id="update_surname" defaultValue={sur} className="form-control"/>
                    <input type="text" ref="update_firstname" placeholder="Имя" id="update_firstname" defaultValue={fir} className="form-control"/>
                    <input type="text" ref="update_lastname" placeholder="Отчество" id="update_lastname" defaultValue={las} className="form-control"/>

                    <input type="text" ref="update_birthyear" placeholder="Год рождения" id="update_birthyear" defaultValue={bir} className="form-control"/>

                    <button onClick={this.Update} className="btn btn-outline-dark">Сохранить</button>

                </form>
            </div>
        )

    }

    renderNull() {
        return (
            <div>
            </div>
        )
    }

    render() {

        if (this.props.showOnUpdate) {
            return this.renderUp();
        } else {
            return this.renderNull();
        }
    }

}

export default UpdatePatient;