import React from 'react'
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material'
import { Link } from 'react-router-dom'

const Header = () => {
  return (
    <AppBar position="static" color="default" elevation={1}>
      <Toolbar sx={{ justifyContent: 'space-between' }}>
        <Typography variant="h6" color="black">
          Farm2Pot
        </Typography>
        <Box>
          <Button color="inherit" component={Link} to="/signIn">
            Sign In
          </Button>
          <Button color="inherit" component={Link} to="/signUp">
            Sign Up
          </Button>
        </Box>
      </Toolbar>
    </AppBar>
  )
}

export default Header
