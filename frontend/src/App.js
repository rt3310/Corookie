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
<<<<<<< HEAD
                        <Route path={utils.URL.PLAN.CALENDER} element={<pages.Plan />} />
=======
                        <Route path={utils.URL.CALENDAR.BOARD} element={<pages.CalendarBoard />} />
>>>>>>> 2faf8c17ad971d851d5c5f36727136b7bc9a9277
                    </Route>
                </Routes>
            </Router>
        </ThemeProvider>
    )
}

export default App
