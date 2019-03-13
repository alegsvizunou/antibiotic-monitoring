'use strict';

import axios from "axios/index";
import Calendar from 'react-calendar';
const React = require('react');

class CreatePrescribtion extends React.Component{

    constructor(props) {
        super(props);
        this.create = this.create.bind(this);
        this.changeShow = this.changeShow.bind(this);
        this.renderNull = this.renderNull.bind(this);
        this.renderUp = this.renderUp.bind(this);
        this.renderUpSt = this.renderUpSt.bind(this);
        this.changeRendUp = this.changeRendUp.bind(this);
        this.findAntibiotics = this.findAntibiotics.bind(this);
        this.onChangeSt = this.onChangeSt.bind(this);
        this.showSt = this.showSt.bind(this);
        this.state = {showOnCreate: false, antibiotics: [],
            showStateSt: false, startcomment: [],
            antibiotic: [], dosage: [], multiplicity: [] };

    }

    componentDidMount() {
        this.findAntibiotics();
    }

    changeShow(){
        this.setState({
            showOnCreate: !this.state.showOnCreate
        });
        this.setState({
            startcomment: "",
            antibiotic: "",
            dosage: "",
            multiplicity: ""
        });
    }

    findAntibiotics(){
        axios.get('http://localhost:8080/api/antibiotics')
            .then(res => {
                const antibiotics = res.data;
                this.setState({
                    antibiotics: antibiotics._embedded.antibiotics
                });

            });

    }
    create(e) {
        e.preventDefault();
        var a;
        let d = document.getElementById('select_antibiotic').value;
        a = d.substring(d.lastIndexOf('/') + 1);

        let startdate = this.refs.update_startdate2.value;
        let startcomment = this.refs.update_startcomment2.value;
        let dosage = this.refs.update_dosage2.value;
        let multiplicity = this.refs.update_multiplicity2.value;


        class Antibiotic {
            constructor(id) {
                this.id=id;
            }
        }

        class Prescribtion {
            constructor(startdate, startcomment, dosage, multiplicity, antibiotic, b) {
                this.startdate=startdate;
                this.startcomment=startcomment;
                this.dosage=dosage;
                this.multiplicity=multiplicity;
                this.antibiotic=antibiotic;
                this.b=b;
            }
        }

        this.props.onCreate(new Prescribtion(startdate, startcomment, dosage, multiplicity, new Antibiotic(a), true));
        this.changeShow();

        this.setState({
            startcomment: "",
            antibiotic: "",
            dosage: "",
            multiplicity: ""
        });
    }


    renderUp() {

        let antibiotics = this.state.antibiotics.map(antibiotic =>
            <option value = {antibiotic._links.self.href}>{antibiotic.name}</option>
        );

        return (
            <div className="col-6">

                <form ref="myForm" onChange={this.changeRendUp}>
                    <input type="text" ref="update_startdate2" onClick={this.showSt} placeholder="Дата назначения" className="form-control" id="updateDateSt"/>

                    <input type="text" ref="update_startcomment2" value={this.state.startcomment} placeholder="Комментарий при назначении" className="form-control" id="startcomment"/>
                    <select id="select_antibiotic" ref="select_antibiotic" value={this.state.antibiotic} defaultValue={this.state.antibiotics[0]._links.self.href} >
                        {antibiotics}
                    </select>

                    <input type="text" ref="update_dosage2" value={this.state.dosage} placeholder="Доза" className="form-control" id="dosage"/>
                    <input type="text" ref="update_multiplicity2" value={this.state.multiplicity} placeholder="Кратность" className="form-control" id="multiplicity"/>

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
            startcomment: this.refs.update_startcomment2.value,
            antibiotic: this.refs.select_antibiotic.value,
            dosage: this.refs.update_dosage2.value,
            multiplicity: this.refs.update_multiplicity2.value
        });
    }

    renderUpSt() {

        let antibiotics = this.state.antibiotics.map(antibiotic =>
            <option value = {antibiotic._links.self.href}>{antibiotic.name}</option>
        );


        return (
            <div className="col-6">

                <form ref="myForm" onChange={this.changeRendUp}>
                    <input type="text" ref="update_startdate2" onClick={this.showSt} placeholder="Дата назначения" className="form-control" id="updateDateSt"/>
                    <Calendar onChange={this.onChangeSt} />
                    <input type="text" ref="update_startcomment2" value={this.state.startcomment} placeholder="Комментарий при назначении" className="form-control" id="startcomment"/>
                    <select id="select_antibiotic" ref="select_antibiotic" value={this.state.antibiotic} >
                        {antibiotics}
                    </select>

                    <input type="text" ref="update_dosage2" value={this.state.dosage} placeholder="Доза" className="form-control" id="dosage"/>
                    <input type="text" ref="update_multiplicity2" value={this.state.multiplicity} placeholder="Кратность" className="form-control" id="multiplicity"/>

                    <button onClick={this.create} className="btn btn-outline-dark">Сохранить</button>
                    <button onClick={this.changeShow} className="btn btn-outline-dark">Закрыть</button>
                </form>
            </div>
        )

    }

    renderNull() {
        return (
            <div>
                <button onClick={this.changeShow} className="btn btn-outline-dark">Создать назначение</button>
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


export default CreatePrescribtion;