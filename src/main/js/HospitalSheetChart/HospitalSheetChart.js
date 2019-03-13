'use strict';

import {Link} from 'react-router-dom';
const React = require('react');


class HospitalSheetChart extends React.Component{

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
        this.handleUpdate = this.handleUpdate.bind(this);
        this.handleOpen = this.handleOpen.bind(this);
        this.rendUp = this.rendUp.bind(this);
        this.rendNorm = this.rendNorm.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.hospitalSheetChart);
    }

    handleUpdate() {
        this.props.onUpdate(this.props.hospitalSheetChart);
    }

    handleOpen() {
        this.props.onOpen(this.props.hospitalSheetChart);
    }



    rendUp() {
        return (

            <div>
                <tr className="d-flex">
                    <td className="col-2">{this.props.hospitalSheetChart.number}</td>
                    <td className="col-2">{this.props.hospitalSheetChart.startdate}</td>
                    <td className="col-2">{this.props.hospitalSheetChart.enddate}</td>

                    <td className="col-1">{this.props.hospitalSheetChart.clinicNumber}</td>
                    <td className="col-1">{this.props.hospitalSheetChart.city}</td>
                    <td className="col-1"><button onClick={this.handleDelete} className="btn btn-outline-dark">Удалить</button></td>
                    <td className="col-1"><button onClick={this.handleUpdate} className="btn btn-outline-dark">Изм-ть</button></td>

                    <td className="col-1"><Link to={{pathname: "/patients/hospitalSheetCharts/prescribtions",
                        state:{selfHrefHospitalSheetChart: this.props.hospitalSheetChart._links.self.href, links2: this.props.links2} }}>
                        <button type="button" className="btn btn-outline-dark" >
                            К наз-ям
                        </button>
                    </Link></td>
                </tr>
            </div>
        )
    }

    rendNorm() {
        return (



            <div>
                <tr className="d-flex">
                    <td className="col-2">{this.props.hospitalSheetChart.number}</td>
                    <td className="col-2">{this.props.hospitalSheetChart.startdate}</td>
                    <td className="col-2">{this.props.hospitalSheetChart.enddate}</td>

                    <td className="col-1">{this.props.hospitalSheetChart.clinicNumber}</td>
                    <td className="col-1">{this.props.hospitalSheetChart.city}</td>

                    <td className="col-1"><button onClick={this.handleDelete} className="btn btn-outline-dark">Удалить</button></td>

                    <td className="col-1"><Link to={{pathname: "/patients/hospitalSheetCharts/prescribtions",
                        state:{selfHrefHospitalSheetChart: this.props.hospitalSheetChart._links.self.href, links2: this.props.links2} }}>
                        <button type="button" className="btn btn-outline-dark" >
                            К наз-ям
                        </button>
                    </Link></td>
                </tr>
            </div>
        )
    }

    render() {
        if (this.props.hospitalSheetChart.b == false) {
            return this.rendNorm();
        } else {
            return this.rendUp();
        }
    }
}

export default HospitalSheetChart;