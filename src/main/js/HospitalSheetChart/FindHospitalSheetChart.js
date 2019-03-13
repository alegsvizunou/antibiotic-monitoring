'use strict';

import HospitalSheetChartList from "./HospitalSheetChartList";
const React = require('react');
import axios from 'axios';

class FindHospitalSheetChart extends React.Component {

    constructor(props) {
        super(props);
        this.state = {hospitalSheetCharts: [], links: [], hospitalSheetChartUp: [], showOnUpdate: false,
            links2: this.props.location.state.links2, errors: []};
        this.onDelete = this.onDelete.bind(this);
        this.onUpdate = this.onUpdate.bind(this);
        this.onSaveUp = this.onSaveUp.bind(this);
        this.onStart = this.onStart.bind(this);
        this.onCreate = this.onCreate.bind(this);
        this.onClose = this.onClose.bind(this);

    }



    onDelete(hospitalSheetChart) {
        this.setState({
            errors: null
        });
        axios.delete(hospitalSheetChart._links.self.href).finally(()=> this.onStart());
    }

    componentDidMount() {
        this.onStart();
    }

    onStart() {
        axios.get(this.props.location.state.selfHrefPatient + "/hospitalSheetCharts?", {params: {projection: "minimal"}})
            .then(res => {
                //   const hospitalSheetCharts = res.data;

                this.setState({
                    hospitalSheetCharts: res.data._embedded.hospitalSheetCharts,
                    links: res.data._links.self.href,
                    linkForDoc: res.data._embedded.hospitalSheetCharts
                });
            });
    }

    onCreate(hospitalSheetChart) {

        var allErr = null;
        var allErrN = null;

        axios.post(this.props.location.state.selfHrefPatient + "/hospitalSheetCharts", hospitalSheetChart)
        // .then(res => {
        //   //  const hospitalSheetChart = res.data;
        //
        // })
            .then(()=> {
                this.setState({
                    errors: null
                });

            })
            .catch(error=> {
                    error.response.data.map(err => {
                        allErr = allErr + "; " + err.defaultMessage;
                        allErrN = allErr.substr(6, (allErr.length - 1));
                    });
                    this.setState({
                        errors: allErrN
                    });
                }
            )
            .finally(()=> this.onStart());

    }


    onUpdate(hospitalSheetChart) {

        this.setState({
            hospitalSheetChartUp: hospitalSheetChart,
            showOnUpdate: true

        });

    }

    onClose() {

        this.setState({
            showOnUpdate: false

        });

    }


    onSaveUp(enddate, hospitalSheetChart){

        var allErr = null;
        var allErrN = null;

        hospitalSheetChart.enddate=enddate;
        hospitalSheetChart.b=false;

        axios.put(hospitalSheetChart._links.self.href, hospitalSheetChart)
        // .then(res => {
        //   //  const hospitalSheetChart = res.data;
        // })
            .then(()=> {
                this.setState({
                    errors: null
                });

            })
            .catch(error=> {
                    error.response.data.map(err => {
                        allErr = allErr + "; " + err.defaultMessage;
                        allErrN = allErr.substr(6, (allErr.length - 1));
                    });
                    this.setState({
                        errors: allErrN
                    });
                }
            )
            .finally(()=>this.onStart());

        this.setState({
            showOnUpdate: false
        });

    }

    render() {
        return (


            <div className="container justify-content-center">

                <div className="row justify-content-end">
                    <form name='f' action='/logout' method='POST'>
                        <button className="btn btn-outline-dark" type="submit" name="submit"> Logout </button>
                    </form>
                </div>

                <div className="row justify-content-center">
                    <div className="col-10">
                        <p>{this.state.errors}</p>
                    </div>
                </div>

                <HospitalSheetChartList hospitalSheetCharts={this.state.hospitalSheetCharts}
                                        onDelete={this.onDelete}
                                        onUpdate={this.onUpdate}
                                        onSaveUp={this.onSaveUp}
                                        hospitalSheetChartUp={this.state.hospitalSheetChartUp}
                                        onOpen={this.onOpen}
                                        links={this.state.links}
                                        links2={this.state.links2}
                                        showOnUpdate={this.state.showOnUpdate}
                                        onCreate={this.onCreate}
                                        onClose={this.onClose}

                />
            </div>
        )
    }
}

export default FindHospitalSheetChart;