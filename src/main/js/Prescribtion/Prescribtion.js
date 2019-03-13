'use strict';

import {Link} from 'react-router-dom';
const React = require('react');

class Prescribtion extends React.Component{

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
        this.handleUpdate = this.handleUpdate.bind(this);
        this.handleOpen = this.handleOpen.bind(this);
        this.rendUp = this.rendUp.bind(this);
        this.rendNorm = this.rendNorm.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.prescribtion);
    }

    handleUpdate() {
        this.props.onUpdate(this.props.prescribtion);
    }

    handleOpen() {
        this.props.onOpen(this.props.prescribtion);
    }



    rendUp() {
        return (

            <div>
                <tr className="d-flex">
                    <td className="col-1">{this.props.prescribtion.startdate}</td>
                    <td className="col-1">{this.props.prescribtion.startcomment}</td>
                    <td className="col-1">{this.props.prescribtion.doctorSurname}</td>
                    <td className="col-1">{this.props.prescribtion.doctorSpecialtyName}</td>

                    <td className="col-1">{this.props.prescribtion.name} </td>
                    <td className="col-1"><p>{this.props.prescribtion.dosage}мг  {this.props.prescribtion.multiplicity}р/с</p></td>


                    <td className="col-1"><p></p></td>
                    <td className="col-1"><p></p></td>
                    <td className="col-1"><p></p></td>
                    <td className="col-1"><p></p></td>

                    <td className="col-1"><button onClick={this.handleDelete} className="btn btn-outline-dark">Удалить</button></td>
                    <td className="col-1"><button onClick={this.handleUpdate} className="btn btn-outline-dark">Изм-ть</button></td>
                </tr>
            </div>
        )
    }

    rendNorm() {
        return (



            <div>
                <tr className="d-flex">
                    <td className="col-1">{this.props.prescribtion.startdate}</td>
                    <td className="col-1">{this.props.prescribtion.startcomment}</td>
                    <td className="col-1">{this.props.prescribtion.doctorSurname}</td>
                    <td className="col-1">{this.props.prescribtion.doctorSpecialtyName}</td>

                    <td className="col-1">{this.props.prescribtion.name} </td>
                    <td className="col-1"><p>{this.props.prescribtion.dosage}мг  {this.props.prescribtion.multiplicity}р/с</p></td>

                    <td className="col-1">{this.props.prescribtion.enddate}</td>
                    <td className="col-1">{this.props.prescribtion.endcomment}</td>
                    <td className="col-1">{this.props.prescribtion.doctor2Surname}</td>
                    <td className="col-1">{this.props.prescribtion.doctor2SpecialtyName}</td>

                    <td className="col-1"><button onClick={this.handleDelete} className="btn btn-outline-dark">Удалить</button></td>
                </tr>
            </div>
        )
    }

    render() {
        if (this.props.prescribtion.b == false) {
            return this.rendNorm();
        } else {
            return this.rendUp();
        }
    }
}

export default Prescribtion;