import React from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import { ThemeProvider } from 'styled-components'

import * as styles from 'style'
import * as utils from 'utils'
import * as pages from 'pages'
import * as components from 'components'

const App = () => {
    return (
        <ThemeProvider theme={styles.Theme}>
            <styles.GlobalStyles />
            <Router>
                <Routes>
                    <Route path={utils.URL.HOME.MAIN} element={<components.Layout />}>
                        <Route path={utils.URL.HOME.MAIN} element={<pages.Main />} />
                        <Route path={utils.URL.CHAT.TEXT} element={<pages.TextChat />} />
                        <Route path={utils.URL.TASK.BOARD} element={<pages.TaskBoard />} />
                        <Route path={utils.URL.PLAN.CALENDER} element={<pages.Plan />} />
                    </Route>
                    <Route path={utils.URL.LOGIN.LOGIN} element={<pages.Login />} />
                </Routes>
            </Router>
        </ThemeProvider>
    )
}

export default App
