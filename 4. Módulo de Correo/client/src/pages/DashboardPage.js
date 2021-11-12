import { Box, Grid, Container, Typography, Stack, CircularProgress } from '@mui/material';
import { useContext, useEffect, useState } from 'react';
import { DataContext } from 'src/store/GlobalState';
import { getData } from 'src/utils/fetchData';
import Page from '../components/Page';
import EnvioCard from '../components/_dashboard/envio/EnvioCard';

const DashboardPage = () => {

  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);

  const { state } = useContext(DataContext);

  const { auth } = state;

  useEffect(() => {

    getData(`envios/cantidad/enviosPorEstado`, auth.jwt)
      .then(res => {
        setItems(res);
        setLoading(false);
      })
      .catch(e => console.log(e))

  }, [auth.jwt])

  if (loading) return (
    <Page title="Dashboard | Módulo-correo">
      <Container>
        <Stack direction="row" alignItems="center" justifyContent="center">
          <CircularProgress color="primary" />
        </Stack>
      </Container>
    </Page>
  )

  return (
    <Page title="Dashboard | Módulo-correo">
      <Container maxWidth="xl">
        <Box sx={{ pb: 5 }}>
          <Typography variant="h4">Hola, Bienvenido de nuevo</Typography>
        </Box>
        <Grid container spacing={3}>
          {
            items.map(item => (
              <Grid item xs={12} sm={6} md={3} key={item.estado}>
                <EnvioCard item={item} />
              </Grid>
            ))
          }
        </Grid>
      </Container>
    </Page>
  );
}

export default DashboardPage;
