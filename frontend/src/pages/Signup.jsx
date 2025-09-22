import { Box, Button, TextField, Typography } from '@mui/material'

const Signup = () => {
  return (
    <Box sx={{ maxWidth: 400, mx: 'auto', mt: 10 }}>
      <Typography variant="h5" mb={2}>
        Sign Up
      </Typography>
      <TextField fullWidth label="Email" margin="normal" />
      <TextField fullWidth label="Username" margin="normal" />
      <TextField fullWidth label="Password" type="password" margin="normal" />
      <Button variant="contained" fullWidth sx={{ mt: 2 }}>
        Create Account
      </Button>
    </Box>
  )
}

export default Signup
