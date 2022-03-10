import React from "react";
import {
    AppBar,
    Toolbar,
    CssBaseline,
    makeStyles,
    useTheme,
    useMediaQuery,
} from "@material-ui/core";
import { Link } from "react-router-dom";
import DrawerComponent from "./DrawerComponent";
import {getRole, getToken} from "../../utils/Common";
import logotype from "../../assets/logo.png";

const useStyles = makeStyles((theme) => ({
    navlinks: {
        display: "flex",
    },
    logo: {
        cursor: "pointer",
        width: "50px",
        transition: "0.4s",
        "&:hover": {
            width: "55px",
        },
    },
    link: {
        textDecoration: "none",
        color: "white",
        fontSize: "20px",
        marginLeft: theme.spacing(5),
        transition: "0.4s",
        "&:hover": {
            color: "#ECF87F",
        },
    },
}));

function Navbar() {
    const classes = useStyles();
    const theme = useTheme();
    const isMobile = useMediaQuery(theme.breakpoints.down("md"));
    const isManager = getRole(getToken()) === "MANAGER";

    return (
        <AppBar position="static" style={{
            backgroundColor: "#013A20"
        }}>
            <CssBaseline />
            <Toolbar>
                <img src={logotype} alt="logo" id="logo" className={classes.logo}/>
                {isMobile ? (
                    <DrawerComponent />
                ) : (
                    <div className={classes.navlinks}>
                        <Link to="/upload" className={classes.link}>
                            Upload
                        </Link>
                        <Link to="/errors" className={classes.link}>
                            Errors
                        </Link>
                        {isManager && <Link to="/transactions" className={classes.link}>Transactions</Link>}
                        {isManager && <Link to="/users" className={classes.link}>Users</Link>}
                        {isManager && <Link to="/users/register" className={classes.link}>Register</Link>}
                        <Link to="/login" className={classes.link}>Re-login</Link>
                    </div>
                )}
            </Toolbar>
        </AppBar>
    );
}
export default Navbar;
