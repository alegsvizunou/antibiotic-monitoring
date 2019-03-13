'use strict';

import Calendar from 'react-calendar';
const React = require('react');

class UpdateHospitalSheetChart extends React.Component{
    constructor(props) {
        super(props);
        this.Update = this.Update.bind(this);
        this.renderUp = this.renderUp.bind(this);
        this.renderUpEn = this.renderUpEn.bind(this);
        this.renderNull = this.renderNull.bind(this);
        this.onChangeEn = this.onChangeEn.bind(this);
        this.showEn = this.showEn.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.state = { showStateEn: false};

    }

    Update(e) {
        e.preventDefault();
        let enddate = this.refs.update_enddate2_.value;
        this.props.onSaveUp(enddate, this.props.hospitalSheetChartUp);

    }

    handleClose() {
        this.props.onClose();
    }

    renderUp() {
        return (
            <div className="col-6">

                <form ref="myForm">

                    <input type="text" ref="update_enddate2_" onClick={this.showEn} placeholder="Дата закрытия" className="form-control" id="updateDateEn_" />
                    <button onClick={this.Update} className="btn btn-outline-dark">Сохранить</button>
                    <button onClick={this.handleClose} className="btn btn-outline-dark">Закрыть</button>
                </form>
            </div>
        )

    }

    onChangeEn (date) {
        var d = date;
        let a = d.getUTCDate() + 1;
        let b = d.getUTCMonth() + 1;
        let c = d.getUTCFullYear();

        document.getElementById('updateDateEn_').value=a + "." + b + "." + c;
        this.setState({
            showStateEn: false

        });

    }

    showEn () {
        this.setState({
            showStateEn: !this.state.showStateEn

        });
    }

    renderUpEn() {
        return (
            <div className="col-6">

                <form ref="myForm">
                    <input type="text" ref="update_enddate2_" onClick={this.showEn} className="form-control" placeholder="Дата закрытия" id="updateDateEn_" />
                    <Calendar onChange={this.onChangeEn} />
                    <button onClick={this.Update} className="btn btn-outline-dark">Сохранить</button>
                    <button onClick={this.handleClose} className="btn btn-outline-dark">Закрыть</button>
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


        if (this.props.showOnUpdate && this.state.showStateEn) {

            return this.renderUpEn();
        } else if(this.props.showOnUpdate){
            return this.renderUp();
        } else  {

            return this.renderNull();
        }
    }
}


export default UpdateHospitalSheetChart;