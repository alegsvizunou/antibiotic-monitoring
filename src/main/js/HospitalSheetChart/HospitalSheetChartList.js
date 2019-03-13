'use strict';

import HospitalSheetChart from "./HospitalSheetChart";
import CreateHospitalSheetChart from "./CreateHospitalSheetChart";
import UpdateHospitalSheetChart from "./UpdateHospitalSheetChart";
const React = require('react');

class HospitalSheetChartList extends React.Component{

    constructor(props) {
        super(props);

    }

    render() {
        let hospitalSheetCharts = this.props.hospitalSheetCharts.map(hospitalSheetChart =>
            <HospitalSheetChart key={hospitalSheetChart._links.self.href} hospitalSheetChart={hospitalSheetChart}
                                onDelete={this.props.onDelete}
                                onUpdate={this.props.onUpdate}
                                onOpen={this.props.onOpen}
                                links2={this.props.links2}
            />
        );

        return (
            <div>
                <div className="row justify-content-center">
                    <CreateHospitalSheetChart links={this.props.links} onCreate={this.props.onCreate}/>
                </div>

                <div className="row justify-content-center">

                    <table className="table table-hover table-light">
                        <thead>
                        <tr className="d-flex">
                            <th className="col-2">Номер</th>
                            <th className="col-2">Дата открытия</th>
                            <th className="col-2">Дата закрытия</th>
                            <th className="col-1">Номер &nbsp;боль-&nbsp;&nbsp;&nbsp;&nbsp;ницы</th>
                            <th className="col-1">Город</th>


                        </tr>
                        </thead>
                        <tbody>
                        {hospitalSheetCharts}
                        </tbody>
                    </table>
                </div>
                <div className="row justify-content-center">
                    <UpdateHospitalSheetChart onSaveUp={this.props.onSaveUp}
                                              hospitalSheetChartUp={this.props.hospitalSheetChartUp}
                                              showOnUpdate={this.props.showOnUpdate}
                                              onClose={this.props.onClose}/>
                </div>
            </div>
        )
    }
}

export default HospitalSheetChartList;