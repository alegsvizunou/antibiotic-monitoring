'use strict';

import PatientList from "./PatientList"
const React = require('react');
import axios from 'axios';

class FindPatient extends React.Component {


    constructor(props) {
        super(props);
        this.state = {patients: [], links: [], patientUp: [], showOnUpdate: false, links2: [], sur: [], fir: [], las: [], errors: []};
        this.onDelete = this.onDelete.bind(this);
        this.onUpdate = this.onUpdate.bind(this);
        this.createPat = this.createPat.bind(this);
        this.onSaveUp = this.onSaveUp.bind(this);
        this.findPat = this.findPat.bind(this);
        this.find = this.find.bind(this);

    }


    onDelete(patient) {

        this.setState({
            errors: null
        });

        axios.delete(patient._links.self.href)
            .finally(()=> {
                    if (!this.state.sur == "" && this.state.fir == "" && this.state.las == "") {
                        axios.get(`http://localhost:8080/api/patients/search/findBySurname`, {params: {surname: this.state.sur}})
                            .then(res => {

                                if (res.data._embedded.patients == null) {
                                    axios.get(res.data._links.self.href).then(res => {

                                        this.setState({
                                            patients: res.data._embedded.patients

                                        });

                                    });
                                } else {
                                    this.setState({
                                        patients: res.data._embedded.patients

                                    });
                                }
                            });

                    }

                    else if (!this.state.sur == "" && !this.state.fir == "" && this.state.las == "") {
                        axios.get(`http://localhost:8080/api/patients/search/findBySurnameAndFirstname`, {
                            params: {
                                surname: this.state.sur,
                                firstname: this.state.fir
                            }
                        })
                            .then(res => {

                                if (res.data._embedded.patients == null) {
                                    axios.get(res.data._links.self.href).then(res => {

                                        this.setState({
                                            patients: res.data._embedded.patients

                                        });

                                    });
                                } else {
                                    this.setState({
                                        patients: res.data._embedded.patients

                                    });
                                }

                            });

                    }
                }
            );



    }

    findPat() {

        this.setState({
            sur: this.refs.surname_patient.value,
            fir: this.refs.firstname_patient.value,
            las: this.refs.lastname_patient.value
        });


    }

    find(e) {

        e.preventDefault();

        this.setState({
            errors: null
        });

        if (!this.state.sur == "" && this.state.fir == "" && this.state.las == "") {
            axios.get(`http://localhost:8080/api/patients/search/findBySurname`, {params: {surname: this.state.sur}})
                .then(res => {
                    const patients = res.data;

                    this.setState({
                        patients: res.data._embedded.patients,
                        links: res.data._links.self.href
                    });
                });

        }

        else if (!this.state.sur == "" && !this.state.fir == "" && this.state.las == "") {
            axios.get(`http://localhost:8080/api/patients/search/findBySurnameAndFirstname`, {
                params: {
                    surname: this.state.sur,
                    firstname: this.state.fir
                }
            })
                .then(res => {
                    const patients = res.data;
                    this.setState({
                        patients: res.data._embedded.patients,
                        links: res.data._links.self.href
                    });
                });

        }

    }

    createPat(patient) {
        var allErr = null;
        var allErrN = null;
        axios.post(`http://localhost:8080/api/patients`, patient)
            .then(()=> {
                this.setState({
                    errors: null
                });

            })
            .catch(error=> {
                    error.response.data.map(err => {
                        allErr = allErr + "; " + err.defaultMessage;
                        allErrN = allErr.substr(6, (allErr.length - 1));
                    });
                    this.setState({
                        errors: allErrN
                    });
                }
            ).finally( ()=> {

            axios.get(`http://localhost:8080/api/patients/search/findBySurnameAndFirstnameAndLastname`, {
                params: {
                    surname: patient.surname,
                    firstname: patient.firstname,
                    lastname: patient.lastname
                }
            }).then(res => {

                if (res.data._embedded.patients == null) {
                    axios.get(res.data._links.self.href).then(res => {

                        this.setState({
                            patients: res.data._embedded.patients

                        });

                    });
                } else {
                    this.setState({
                        patients: res.data._embedded.patients

                    });
                }


            });

        });

    }

    onUpdate(patient) {

        this.setState({
            patientUp: patient,
            showOnUpdate: true

        });

    }


    onSaveUp(firstname, lastname, surname, birthyear, patient){
        var allErr = null;
        var allErrN = null;


        patient.firstname = firstname;
        patient.lastname = lastname;
        patient.surname = surname;
        patient.birthyear = birthyear;


        axios.put(patient._links.self.href, patient)
            .then(()=> {
                this.setState({
                    errors: null
                });

            })
            .catch(error=> {
                    error.response.data.map(err => {
                        allErr = allErr + "; " + err.defaultMessage;
                        allErrN = allErr.substr(6, (allErr.length - 1));
                    });
                    this.setState({
                        errors: allErrN
                    });
                }
            )
            .finally(()=> {
                    if (!this.state.sur == "" && this.state.fir == "" && this.state.las == "") {
                        axios.get(`http://localhost:8080/api/patients/search/findBySurname`, {params: {surname: this.state.sur}})
                            .then(res => {

                                if (res.data._embedded.patients == null) {
                                    axios.get(res.data._links.self.href).then(res => {

                                        this.setState({
                                            patients: res.data._embedded.patients

                                        });

                                    });
                                } else {
                                    this.setState({
                                        patients: res.data._embedded.patients

                                    });
                                }
                            });

                    }

                    else if (!this.state.sur == "" && !this.state.fir == "" && this.state.las == "") {
                        axios.get(`http://localhost:8080/api/patients/search/findBySurnameAndFirstname`, {
                            params: {
                                surname: this.state.sur,
                                firstname: this.state.fir
                            }
                        })
                            .then(res => {

                                if (res.data._embedded.patients == null) {
                                    axios.get(res.data._links.self.href).then(res => {

                                        this.setState({
                                            patients: res.data._embedded.patients

                                        });

                                    });
                                } else {
                                    this.setState({
                                        patients: res.data._embedded.patients

                                    });
                                }

                            });

                    }
                }
            );

        this.setState({
            showOnUpdate: false
        });

    }




    render() {

        return (
            <div className="container justify-content-center">

                <div className="row justify-content-end">
                    <form name='f' action='/logout' method='POST'>
                        <button className="btn btn-outline-dark" type="submit" name="submit"> Logout </button>
                    </form>
                </div>

                <div className="row justify-content-center">
                    <div className="col-10">
                        <p>{this.state.errors}</p>
                    </div>
                </div>

                <div className="row justify-content-center">

                    <div className="col-6">
                        <form ref="myForm">


                            <input type="text" ref="surname_patient" placeholder="Фамилия" onChange={this.findPat} className="form-control"/>
                            <input type="text" ref="firstname_patient" placeholder="Имя" onChange={this.findPat} className="form-control"/>
                            <input type="text" ref="lastname_patient" placeholder="Отчество" onChange={this.findPat} className="form-control"/>

                            <button onClick={this.find} className="btn btn-outline-dark">Поиск</button>
                        </form>
                    </div>


                </div>

                <PatientList patients={this.state.patients}
                             onDelete={this.onDelete}
                             onUpdate={this.onUpdate}
                             onSaveUp={this.onSaveUp}
                             patientUp={this.state.patientUp}
                             onOpen={this.onOpen}
                             links={this.state.links}
                             showOnUpdate={this.state.showOnUpdate}
                             links2={this.state.links2}
                             createPat={this.createPat}
                />

            </div>
        )
    }

}

export default FindPatient;