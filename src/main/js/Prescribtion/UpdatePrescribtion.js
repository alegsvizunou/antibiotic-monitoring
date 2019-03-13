'use strict';

const React = require('react');
import Calendar from 'react-calendar';

class UpdatePrescribtion extends React.Component{
    constructor(props) {
        super(props);
        this.Update = this.Update.bind(this);
        this.changeRendUp = this.changeRendUp.bind(this);
        this.renderUp = this.renderUp.bind(this);
        this.renderUpEn = this.renderUpEn.bind(this);
        this.renderNull = this.renderNull.bind(this);
        this.onChangeEn = this.onChangeEn.bind(this);
        this.showEn = this.showEn.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.state = { showStateEn: false, endcomment: []};

    }


    Update(e) {
        e.preventDefault();
        let enddate = this.refs.update_enddate2_.value;
        let endcomment = this.refs.update_endcomment2.value;
        this.props.onSaveUp(enddate, endcomment, this.props.prescribtionUp);
        this.setState({
            endcomment: ""
        });

    }

    handleClose() {
        this.props.onClose();
        this.setState({
            endcomment: ""
        });
    }

    renderUp() {

        return (
            <div className="col-6">

                <form ref="myForm" onChange={this.changeRendUp}>

                    <input type="text" ref="update_enddate2_" onClick={this.showEn} placeholder="Дата отмены" className="form-control" id="updateDateEn_2" />
                    <input type="text" ref="update_endcomment2" value={this.state.endcomment} placeholder="Комментарий при отмене" className="form-control" id="endcomment"/>
                    <button onClick={this.Update} className="btn btn-outline-dark">Изменить</button>
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

        document.getElementById('updateDateEn_2').value=a + "." + b + "." + c;

        this.setState({
            showStateEn: false

        });

    }

    changeRendUp() {
        this.setState({
            endcomment: this.refs.update_endcomment2.value
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
                <form ref="myForm" onChange={this.changeRendUp}>
                    <input type="text" ref="update_enddate2_" onClick={this.showEn} placeholder="Дата отмены" className="form-control" id="updateDateEn_2" />
                    <Calendar onChange={this.onChangeEn} />
                    <input type="text" ref="update_endcomment2" value={this.state.endcomment} placeholder="Комментарий при отмене" className="form-control" id="endcomment"/>
                    <button onClick={this.Update} className="btn btn-outline-dark">Изменить</button>
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


export default UpdatePrescribtion;