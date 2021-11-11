import { LoadingButton } from "@mui/lab";
import { Card, CardHeader, CircularProgress, Container, Divider, FormControl, Grid, InputLabel, MenuItem, Select, Stack, Typography } from "@mui/material";
import { Box } from "@mui/system";
import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router";
import Scrollbar from "src/components/Scrollbar";
import { DataContext } from "src/store/GlobalState";
import { getData, putData } from "src/utils/fetchData";
import Page from '../components/Page';
import CustomizedSteppers from '../components/Stepper'

const EnvioDetail = () => {

    const { id } = useParams();

    const [envio, setEnvio] = useState({});
    const [loading, setLoading] = useState(true);

    const [estado, setEstado] = useState('');

    const { state } = useContext(DataContext);

    const { auth } = state;

    const estados = [
        'En preparación',
        'Despachado',
        'En Camino',
        'Entregado'
    ];

    const getStep = estado => {
        switch (estado) {
            case 'En preparación':
                return 0;
            case 'Despachado':
                return 1;
            case 'En Camino':
                return 2;
            case 'Entregado':
                return 3;
            default:
                break;
        }
    }

    useEffect(() => {

        getData(`envios/codigo/${id}`, auth.jwt)
            .then(res => {
                setEnvio(res);
                setEstado(res.estado);
                setLoading(false);
            })
            .catch(e => console.log(e))

    }, [auth.jwt, id]);

    if (loading) return (
        <Page title="Envios | Módulo-correo">
            <Container>
                <Stack direction="row" alignItems="center" justifyContent="center">
                    <CircularProgress color="primary" />
                </Stack>
            </Container>
        </Page>
    )

    const updateEnvio = async e => {
        e.preventDefault();

        const response = await putData(`envios/actualizar/${envio.id}`);

        console.log(response.apiResponse.message);

        setEnvio(response.envio);

    }

    return (
        <Page title="Envios | Módulo-correo">
            <Grid container spacing={3}>
                <Grid item xs={12} sm={12} md={12}>
                    <Card >
                        <Box sx={{ px: 3, py: 3 }}>
                            <CustomizedSteppers steps={estados} currentStep={getStep(envio.estado)} />
                        </Box>
                    </Card>
                </Grid>
                <Grid item xs={12} sm={12} md={7}>
                    <Card>
                        <Box sx={{ px: 2, py: 2 }}>
                            <CardHeader title="Envio" />
                            <Divider />
                            <Scrollbar>
                                <Stack spacing={3} sx={{ p: 3, pr: 0 }}>
                                    <Stack direction="row" alignItems="center" spacing={2}>
                                        <Box sx={{ minWidth: 240 }}>
                                            <Typography variant="subtitle2" noWrap>
                                                ID ORDEN: {envio.idOrden}
                                            </Typography>
                                            <Typography variant="subtitle2" noWrap>
                                                FECHA: {envio.createdAt}
                                            </Typography>
                                            <Typography variant="subtitle2" noWrap>
                                                CODIGO DE SEGUIMIENTO: {envio.codigoDeSeguimiento}
                                            </Typography>
                                            <Typography variant="subtitle2" noWrap>
                                                DESCRIPCION: {envio.descripcion}
                                            </Typography>
                                            <Typography variant="subtitle2" noWrap>
                                                DESTINATARIO: {envio.dnidestinatario}
                                            </Typography>
                                            <Typography variant="subtitle2" noWrap>
                                                ESTADO: {envio.estado}
                                            </Typography>
                                        </Box>
                                    </Stack>
                                </Stack>
                            </Scrollbar>
                        </Box>
                    </Card>
                </Grid>
                <Grid item xs={12} sm={12} md={5}>
                    <Card>
                        <Box sx={{ px: 2, py: 2 }}>
                            <CardHeader title="Actualizar Envio" />
                            <Divider />
                            <Scrollbar>
                                <form onSubmit={updateEnvio}>
                                    <Stack spacing={3} sx={{ p: 3, pr: 0 }}>
                                        <FormControl fullWidth>
                                            <InputLabel id="demo-simple-select-label">Estado</InputLabel>
                                            <Select
                                                labelId="demo-simple-select-label"
                                                id="demo-simple-select"
                                                label="Estado"
                                                value={estado}
                                                onChange={e => setEstado(e.target.value)}
                                            >
                                                {
                                                    estados.map(estado => (
                                                        <MenuItem value={estado} key={estado}>{estado}</MenuItem>
                                                    ))
                                                }
                                            </Select>
                                        </FormControl>
                                        <LoadingButton
                                            fullWidth
                                            size="large"
                                            type="submit"
                                            variant="contained"
                                            loading={false}
                                        >
                                            Actualizar
                                        </LoadingButton>
                                    </Stack>
                                </form>
                            </Scrollbar>
                        </Box>
                    </Card>
                </Grid>
            </Grid>
        </Page>
    )
}

export default EnvioDetail;