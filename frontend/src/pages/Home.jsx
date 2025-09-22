import React, { useState } from 'react'
import { Box, Button, Typography } from '@mui/material'
import { adminApi, subscriptionApi, userApi } from '../api/apiClient'

const Home = () => {
  const [message, setMessage] = useState('')

  const handleTest = async api => {
    try {
      const data = await api.get('/test')
      setMessage(`${api.name} 테스트 성공!`)
      console.log(`${api.name} 테스트 결과:`, data)
    } catch (err) {
      setMessage(err.message)
      console.error(err)
    }
  }

  return (
    <Box sx={{ textAlign: 'center', py: 10, backgroundColor: '#fff' }}>
      <Typography variant="h3" gutterBottom>
        Bizwell
      </Typography>

      <Box sx={{ my: 2 }}>
        <Button variant="outlined" onClick={() => handleTest(userApi)}>
          User Test
        </Button>
      </Box>

      <Box sx={{ my: 2 }}>
        <Button variant="outlined" onClick={() => handleTest(subscriptionApi)}>
          Subscription Test
        </Button>
      </Box>

      <Box sx={{ my: 2 }}>
        <Button variant="outlined" onClick={() => handleTest(adminApi)}>
          Admin Test
        </Button>
      </Box>

      {message && (
        <Typography variant="body1" sx={{ mt: 3, color: 'red' }}>
          {message}
        </Typography>
      )}
    </Box>
  )
}

export default Home
