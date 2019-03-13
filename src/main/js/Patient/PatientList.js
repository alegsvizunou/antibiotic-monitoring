'use strict';

import Patient from "./Patient";
import CreatePatient from "./CreatePatient";
import UpdatePatient from "./UpdatePatient";
const React = require('react');

class PatientList extends React.Component{

    constructor(props) {
        super(props);

    }

    render() {
        let patients = this.props.patients.map(patient =>
            <Patient key={patient._links.self.href} patient={patient}
                     onDelete={this.props.onDelete}
                     onUpdate={this.props.onUpdate}
                     onOpen={this.props.onOpen}
                     links2={this.props.links2}
            />
        );
        return (
            <div>
                <div className="row justify-content-center">
                    <CreatePatient links={this.props.links}
                                   createPat={this.props.createPat}/>
                </div>


                <div className="row justify-content-center">

                    <table className="table table-hover table-light">
                        <thead>
                        <tr className="d-flex">
                            <th className="col-2">Фамилия</th>
                            <th className="col-2">Имя</th>
                            <th className="col-2">Отчество</th>
                            <th className="col-2">Год рождения</th>

                        </tr>

                        </thead>
                        <tbody>
                        {patients}
                        </tbody>
                    </table>
                </div>

                <div className="row justify-content-center">

                    <UpdatePatient onSaveUp={this.props.onSaveUp}
                                   patientUp={this.props.patientUp}
                                   showOnUpdate={this.props.showOnUpdate}/>
                </div>
            </div>
        )
    }
}

export default PatientList;