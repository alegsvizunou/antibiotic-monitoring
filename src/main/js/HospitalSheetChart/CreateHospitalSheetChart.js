'use strict';

import Calendar from 'react-calendar';
const React = require('react');
import axios from 'axios';

class CreateHospitalSheetChart extends React.Component{

    constructor(props) {
        super(props);
        this.create = this.create.bind(this);
        this.changeShow = this.changeShow.bind(this);
        this.renderNull = this.renderNull.bind(this);
        this.renderUp = this.renderUp.bind(this);
        this.renderUpSt = this.renderUpSt.bind(this);
        this.findClinics = this.findClinics.bind(this);
        this.findCity = this.findCity.bind(this);
        this.onChangeSt = this.onChangeSt.bind(this);
        this.showSt = this.showSt.bind(this);
        this.changeRendUp = this.changeRendUp.bind(this);
        this.state = {showOnCreate: false, cities: [], clinics: [], city: [],
            showStateSt: false, number: [], select: [] };

    }

    componentDidMount() {
        this.findClinics();
    }

    changeShow(){
        this.setState({
            showOnCreate: !this.state.showOnCreate
        });
        this.setState({
            number: "",
            select: ""
        });

    }

    findClinics(){
        axios.get('http://localhost:8080/api/clinics/search/findClinicsByDoctorAuthenticated')

            .then(res => {

                const clinics = res.data;
                this.setState({
                    clinics: clinics._embedded.clinics
                });

                axios.get(res.data._embedded.clinics[0]._links.self.href + "/city?", {params: {projection: "minimal"}})
                    .then(res => {

                            this.setState({
                                city: res.data

                            });
                        }
                    );


            });
    }

    findCity(event) {
        axios.get(event.target.value + "/city?", {params: {projection: "minimal"}})
            .then(res => {
                this.setState({
                    city: res.data

                });
            });
    }


    create(e) {
        e.preventDefault();
        var a;
        let d = document.getElementById('select_clinic').value;
        a = d.substring(d.lastIndexOf('/') + 1);

        let startdate = this.refs.update_startdate2.value;
        let number = this.refs.update_number2.value;
        class Clinic {
            constructor(id) {
                this.id=id;
            }
        }

        class HospitalSheetChart {
            constructor(startdate, number, clinic, b) {
                this.startdate=startdate;
                this.number=number;
                this.clinic=clinic;
                this.b=b;
            }
        }

        this.props.onCreate(new HospitalSheetChart(startdate, number, new Clinic(a), true));
        this.changeShow();
        this.setState({
            number: "",
            select: ""
        });

    }


    renderUp() {

        let clinics = this.state.clinics.map(clinic =>
            <option value = {clinic._links.self.href}>{clinic.number}</option>
        );

        return (
            <div className="col-6">

                <form ref="myForm" onChange={this.changeRendUp}>
                    <input type="text" ref="update_startdate2" onClick={this.showSt} placeholder="Дата открытия" className="form-control" id="updateDateSt" />
                    <input type="text" ref="update_number2" placeholder="Номер истории" className="form-control" id="updateNumber" value={this.state.number}/>
                    <p>{this.state.city.name}</p>
                    <form onChange={this.findCity}>
                        <select id="select_clinic"  ref="select_clinic" value={this.state.select} defaultValue={this.state.clinics[0]._links.self.href}>
                            {clinics}
                        </select>
                    </form>
                    <button onClick={this.create} className="btn btn-outline-dark">Сохранить</button>
                    <button onClick={this.changeShow} className="btn btn-outline-dark">Закрыть</button>

                </form>
            </div>
        )

    }

    onChangeSt (date) {
        let d = date;

        let a = d.getUTCDate() + 1;
        let b = d.getUTCMonth() + 1;
        let c = d.getUTCFullYear();

        document.getElementById('updateDateSt').value=a + "." + b + "." + c;
        this.setState({
            showStateSt: false
        });

    }
    showSt () {
        this.setState({
            showStateSt: !this.state.showStateSt

        });


    }

    changeRendUp() {
        this.setState({
            number: this.refs.update_number2.value,
            select: this.refs.select_clinic.value
        });
    }

    renderUpSt() {



        let clinics = this.state.clinics.map(clinic =>
            <option value = {clinic._links.self.href}>{clinic.number}</option>
        );



        return (
            <div className="col-6">

                <form ref="myForm" onChange={this.changeRendUp} >
                    <input type="text" ref="update_startdate2" onClick={this.showSt} placeholder="Дата открытия" className="form-control" id="updateDateSt" />
                    <Calendar onChange={this.onChangeSt} />
                    <input type="text" ref="update_number2" placeholder="Номер истории" className="form-control" id="updateNumber1" value={this.state.number}/>

                    <p>{this.state.city.name}</p>
                    <form onChange={this.findCity}>
                        <select id="select_clinic" ref="select_clinic" value={this.state.select}>
                            {clinics}
                        </select>
                    </form>
                    <button onClick={this.create} className="btn btn-outline-dark">Сохранить</button>
                    <button onClick={this.changeShow} className="btn btn-outline-dark">Закрыть</button>
                </form>
            </div>
        )

    }

    renderNull() {
        return (
            <div>
                <button onClick={this.changeShow} className="btn btn-outline-dark">Создать карточку</button>
            </div>
        )
    }

    render() {

        if (this.state.showOnCreate && this.state.showStateSt ){
            return this.renderUpSt();
        }

        else if (this.state.showOnCreate) {

            return this.renderUp();
        } else {
            return this.renderNull();
        }
    }



}

export default CreateHospitalSheetChart;