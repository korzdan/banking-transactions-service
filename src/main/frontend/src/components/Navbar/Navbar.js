import React from "react";
import {
    AppBar,
    Toolbar,
    CssBaseline,
    Typography,
    makeStyles,
    useTheme,
    useMediaQuery,
} from "@material-ui/core";
import { Link } from "react-router-dom";
import DrawerComponent from "./DrawerComponent";

const useStyles = makeStyles((theme) => ({
    navlinks: {
        display: "flex",
    },
    logo: {
        flexGrow: "1",
        cursor: "pointer",
    },
    link: {
        textDecoration: "none",
        color: "white",
        fontSize: "20px",
        marginLeft: theme.spacing(20),
        "&:hover": {
            color: "yellow",
            borderBottom: "1px solid white",
        },
    },
}));

function Navbar() {
    const classes = useStyles();
    const theme = useTheme();
    const isMobile = useMediaQuery(theme.breakpoints.down("md"));

    return (
        <AppBar position="static" style={{
            backgroundColor: "#59981A"
        }}>
            <CssBaseline />
            <Toolbar>
                <Typography variant="h5" className={classes.logo}>
                    BTA
                </Typography>
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
                    </div>
                )}
            </Toolbar>
        </AppBar>
    );
}
export default Navbar;
