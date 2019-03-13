'use strict';

import FindPatient from './Patient/FindPatient';
import FindHospitalSheetChart from './HospitalSheetChart/FindHospitalSheetChart';
import FindPrescribtion from './Prescribtion/FindPrescribtion';
import LoginPage from './LoginPage';
import { Route, BrowserRouter } from 'react-router-dom';
import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory();
const React = require('react');
const ReactDOM = require('react-dom');

class Navigator extends React.Component {

    render() {
        return (
            <BrowserRouter history={history} >
                <div>


                    <Route exact path={"/"} component={FindPatient} />
                    <Route path={"/login"} component={LoginPage} />
                    <Route path={"/patients/hospitalSheetCharts"} exact component={FindHospitalSheetChart} />
                    <Route path={"/patients/hospitalSheetCharts/prescribtions"} exact component={FindPrescribtion} />

                </div>
            </BrowserRouter>
        )
    }
}



ReactDOM.render(
    <Navigator />,
    document.getElementById('react')
);
