import Page from '../components/Page';
import { Link, useLocation } from 'react-router-dom';
import { useContext, useEffect, useState } from 'react';
import { CircularProgress, Container, Stack, Grid, Card, Box, Typography } from '@mui/material';
import { DataContext } from 'src/store/GlobalState';
import { getData } from 'src/utils/fetchData';
import SearchNotFound from 'src/components/SearchNotFound';

const SearchPage = () => {

    const search = useLocation().search;
    const keyword = new URLSearchParams(search).get('query');

    const [envios, setEnvios] = useState([]);
    const [loading, setLoading] = useState(true);

    const { state } = useContext(DataContext);

    const { auth } = state;

    useEffect(() => {

        console.log(keyword)

        getData(`envios/search?keyword=${keyword}`, auth.jwt)
            .then(res => {
                setEnvios(res);
                setLoading(false);
            })
            .catch(e => console.log(e))

    }, [auth.jwt, keyword]);

    if (loading) return (
        <Page title="Envios | Módulo-correo">
            <Container>
                <Stack direction="row" alignItems="center" justifyContent="center">
                    <CircularProgress color="primary" />
                </Stack>
            </Container>
        </Page>
    )

    return (
        <Page title="Envios | Módulo-correo">
            <Grid container spacing={3}>
                {
                    envios.length === 0 && (
                        <Grid item xs={12} sm={12} md={12}>
                            <Card>
                                <Box sx={{ px: 3, py: 2 }}>
                                    <SearchNotFound searchQuery={keyword} />
                                </Box>
                            </Card>
                        </Grid>
                    )
                }

                {
                    envios.map(envio => (
                        <Grid item xs={12} sm={12} md={12} key={envio.id}>
                            <Card>
                                <Link to={`/dashboard/envios/${envio.codigoDeSeguimiento}`}>
                                    <Box sx={{ px: 3, py: 2 }}>
                                        <Typography variant="subtitle2">
                                            {envio.codigoDeSeguimiento + ' - ' + envio.estado + ' - ' + envio.dnidestinatario}
                                        </Typography>
                                    </Box>
                                </Link>
                            </Card>
                        </Grid>
                    ))
                }
            </Grid>
        </Page>
    )
}

export default SearchPage;