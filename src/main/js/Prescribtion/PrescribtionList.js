'use strict';

import Prescribtion from "./Prescribtion";
import CreatePrescribtion from "./CreatePrescribtion";
import UpdatePrescribtion from "./UpdatePrescribtion";
const React = require('react');

class PrescribtionList extends React.Component{

    constructor(props) {
        super(props);

    }

    render() {
        let prescribtions = this.props.prescribtions.map(prescribtion =>
            <Prescribtion key={prescribtion._links.self.href} prescribtion={prescribtion}
                          onDelete={this.props.onDelete}
                          onUpdate={this.props.onUpdate}
                          onOpen={this.props.onOpen}
                          links2={this.props.links2}
            />
        );

        return (
            <div>

                <div className="row justify-content-center">
                    <CreatePrescribtion links={this.props.links} onCreate={this.props.onCreate}/>
                </div>

                <div className="row justify-content-center">
                    <table className="table table-hover table-light">
                        <thead>
                        <tr className="d-flex">
                            <th className="col-1">Дата &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;наз-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;наче-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ния</th>
                            <th className="col-1">Коммен-тарий &nbsp;&nbsp;&nbsp;при наз-начении</th>
                            <th className="col-1">Фами-&nbsp;&nbsp;&nbsp;лия наз-начив-&nbsp;шего</th>
                            <th className="col-1">Специ-&nbsp;&nbsp;альность назна-&nbsp;&nbsp;&nbsp;чив-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;шего</th>
                            <th className="col-1">Анти-&nbsp;&nbsp;&nbsp;&nbsp;биотик</th>
                            <th className="col-1">Доза и &nbsp;&nbsp;крат-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ность</th>
                            <th className="col-1">Дата от-мены</th>
                            <th className="col-1">Коммен-тарий &nbsp;&nbsp;&nbsp;при от-&nbsp;&nbsp;мене</th>
                            <th className="col-1">Фами-&nbsp;&nbsp;&nbsp;лия от-&nbsp;менив-&nbsp;&nbsp;шего</th>
                            <th className="col-1">Специ-&nbsp;&nbsp;альность отме-&nbsp;&nbsp;&nbsp;&nbsp;нившего</th>

                        </tr>

                        </thead>

                        <tbody>
                        {prescribtions}
                        </tbody>

                    </table>
                </div>

                <div className="row justify-content-center">

                    <UpdatePrescribtion onSaveUp={this.props.onSaveUp}
                                        prescribtionUp={this.props.prescribtionUp}
                                        showOnUpdate={this.props.showOnUpdate}
                                        onClose={this.props.onClose}
                    />
                </div>
            </div>
        )
    }
}

export default PrescribtionList;