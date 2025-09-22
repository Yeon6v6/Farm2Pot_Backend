import React from 'react'
import { Grid, Card, CardMedia, CardContent, Typography } from '@mui/material'
import { Link } from 'react-router-dom'

const products = [
  {
    id: 1,
    name: 'Basic T-Shirt',
    image: 'https://via.placeholder.com/300',
    price: '$25',
  },
  {
    id: 2,
    name: 'Slim Jeans',
    image: 'https://via.placeholder.com/300',
    price: '$50',
  },
]

const ProductList = () => {
  return (
    <Grid container spacing={3} p={4}>
      {products.map(product => (
        <Grid
          item
          xs={12}
          sm={6}
          md={4}
          key={product.id}
          component={Link}
          to={`/products/${product.id}`}
        >
          <Card>
            <CardMedia component="img" image={product.image} height="300" />
            <CardContent>
              <Typography variant="h6">{product.name}</Typography>
              <Typography color="textSecondary">{product.price}</Typography>
            </CardContent>
          </Card>
        </Grid>
      ))}
    </Grid>
  )
}

export default ProductList
