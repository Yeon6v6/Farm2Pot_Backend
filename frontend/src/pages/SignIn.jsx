import { Box, Button, TextField, Typography } from '@mui/material'

const SignIn = () => {
  return (
    <Box sx={{ maxWidth: 400, mx: 'auto', mt: 10 }}>
      <Typography variant="h5" mb={2}>
        Sign In
      </Typography>
      <TextField fullWidth label="Email" margin="normal" />
      <TextField fullWidth label="Password" type="password" margin="normal" />
      <Button variant="contained" fullWidth sx={{ mt: 2 }}>
        Sign In
      </Button>
    </Box>
  )
}

export default SignIn
