'use strict';

import {Link} from 'react-router-dom';
const React = require('react');

class Patient extends React.Component{

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
        this.handleUpdate = this.handleUpdate.bind(this);
        this.handleOpen = this.handleOpen.bind(this);
    }

    componentDidMount() {

    }

    handleDelete() {
        this.props.onDelete(this.props.patient);
    }

    handleUpdate() {
        this.props.onUpdate(this.props.patient);
    }

    handleOpen() {
        this.props.onOpen(this.props.patient);
    }

    render() {
        return (

            <div>

                <tr className="d-flex ">
                    <td className="col-2">{this.props.patient.surname}</td>
                    <td className="col-2">{this.props.patient.firstname}</td>
                    <td className="col-2">{this.props.patient.lastname}</td>

                    <td className="col-2">{this.props.patient.birthyear}</td>


                    <td className="col-1"><button onClick={this.handleDelete} className="btn btn-outline-dark">Удалить</button></td>
                    <td className="col-1"><button onClick={this.handleUpdate} className="btn btn-outline-dark">Изм-ть</button></td>

                    <td className="col-1">

                        <Link to={{pathname: "/patients/hospitalSheetCharts",
                            state:{selfHrefPatient: this.props.patient._links.self.href, links2: this.props.links2} }}>
                            <button type="button" className="btn btn-outline-dark" >
                                К кар-кам
                            </button>

                        </Link>

                    </td>
                </tr>

            </div>
        )
    }
}

export default Patient;