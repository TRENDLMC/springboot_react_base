import { SERVER_URL } from "../constants";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Carlist from "./Carlist";
import { useState } from "react";
import Snackbar from "@mui/material/Snackbar";

function Login() {
  const [user, setUser] = useState({
    username: "",
    password: "",
  });
  const [open, setOpen] = useState(false);
  const [isAuthenticated, SetAuth] = useState(false);
  const handleChange = (event) => {
    setUser({ ...user, [event.target.name]: event.target.value });
  };
  const logout = () => {
    sessionStorage.removeItem("jwt");
    SetAuth(false);
  };
  const login = () => {
    fetch(SERVER_URL + "login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    })
      .then((res) => {
        const jwtToken = res.headers.get("Authorization");
        if (jwtToken !== null) {
          sessionStorage.setItem("jwt", jwtToken);
          SetAuth(true);
        } else {
          setOpen(true);
        }
      })
      .catch((err) => alert(err));
  };
  if (isAuthenticated) {
    return <Carlist />;
  } else {
    return (
      <div>
        <Stack spacing={2} alignItems="center" mt={2}>
          <TextField name="username" label="Username" onChange={handleChange} />
          <TextField name="password" label="Password" onChange={handleChange} />
          <Button variant="outlined" color="primary" onClick={login}>
            Login
          </Button>
        </Stack>
        <Snackbar
          open={open}
          autoHideDuration={3000}
          onClose={() => {
            setOpen(false);
          }}
          message="Login failed: Check your username and password"
        />
      </div>
    );
  }
}

export default Login;
