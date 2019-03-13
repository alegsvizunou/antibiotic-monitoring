'use strict';

import PrescribtionList from "./PrescribtionList"
const React = require('react');
import axios from 'axios';

class FindPrescribtion extends React.Component {

    constructor(props) {
        super(props);
        this.state = {prescribtions: [], links: [], prescribtionUp: [], showOnUpdate: false,
            links2: this.props.location.state.links2,
            selfHrefHospitalSheetChart: this.props.location.state.selfHrefHospitalSheetChart, errors: []};
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
        axios.get(this.state.selfHrefHospitalSheetChart + "/prescribtions?", {params: {projection: "minimalFalse"}})
            .then(res => {
                const prescribtions = res.data;
                this.setState({
                    prescribtions: res.data._embedded.prescribtions,
                    links: res.data._links.self.href
                });
            });

    }

    onCreate(prescribtion) {
        var allErr = null;
        var allErrN = null;

        axios.post(this.state.selfHrefHospitalSheetChart + "/prescribtions", prescribtion)
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

    onClose() {
        this.setState({
            showOnUpdate: false
        });

    }

    onUpdate(prescribtion) {
        this.setState({
            prescribtionUp: prescribtion,
            showOnUpdate: true
        });
    }



    onSaveUp(enddate, endcomment, prescribtion){

        var allErr = null;
        var allErrN = null;

        prescribtion.enddate=enddate;
        prescribtion.endcomment=endcomment;
        prescribtion.b=false;

        axios.put(prescribtion._links.self.href, prescribtion)
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

                <PrescribtionList prescribtions={this.state.prescribtions}
                                  onDelete={this.onDelete}
                                  onUpdate={this.onUpdate}
                                  onSaveUp={this.onSaveUp}
                                  prescribtionUp={this.state.prescribtionUp}
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

export default FindPrescribtion;