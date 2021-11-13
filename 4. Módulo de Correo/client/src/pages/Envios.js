import { useContext, useEffect, useState } from 'react';
import {
  Card,
  Table,
  Stack,
  TableRow,
  TableBody,
  TableCell,
  Container,
  Typography,
  TableContainer,
  TablePagination,
  CircularProgress
} from '@mui/material';
import Page from '../components/Page';
import Label from '../components/Label';
import Scrollbar from '../components/Scrollbar';
import { DataContext } from 'src/store/GlobalState';
import { getData } from 'src/utils/fetchData';
import { EnvioListHead, EnvioMoreMenu } from '../components/_dashboard/envio';
import { fDateTime } from 'src/utils/formatTime';

const TABLE_HEAD = [
  { id: 'codigoDeSeguimiento', label: 'Código De Seguimiento', alignRight: false },
  { id: 'createdAt', label: 'Fecha', alignRight: false },
  { id: 'descripcion', label: 'Descripcion', alignRight: false },
  { id: 'dnidestinatario', label: 'Destinatario', alignRight: false },
  { id: 'estado', label: 'Estado', alignRight: false },
];

const Envios = () => {

  const [page, setPage] = useState(0);
  const [order, setOrder] = useState('asc');
  const [orderBy, setOrderBy] = useState('descripcion');
  const [rowsPerPage, setRowsPerPage] = useState(5);

  const [envios, setEnvios] = useState([]);
  const [loading, setLoading] = useState(true);

  const { state } = useContext(DataContext);

  const { auth } = state;

  useEffect(() => {

    getData(`envios`, auth.jwt)
      .then(res => {
        setEnvios(res);
        setLoading(false);
      })
      .catch(e => console.log(e))

  }, [auth.jwt]);


  if (loading) return (
    <Page title="Envios | Módulo-correo">
      <Container>
        <Stack direction="row" alignItems="center" justifyContent="center">
          <CircularProgress color="primary" />
        </Stack>
      </Container>
    </Page>
  )

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === 'asc';
    setOrder(isAsc ? 'desc' : 'asc');
    setOrderBy(property);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <Page title="Envios | Módulo-correo">
      <Container>
        <Stack direction="row" alignItems="center" justifyContent="space-between" mb={4}>
          <Typography variant="h4" gutterBottom>
            Envios
          </Typography>
        </Stack>

        <Card>
          <Scrollbar>
            <TableContainer sx={{ minWidth: 800 }}>
              <Table>
                <EnvioListHead
                  order={order}
                  orderBy={orderBy}
                  headLabel={TABLE_HEAD}
                  rowCount={envios.length}
                  onRequestSort={handleRequestSort}
                />
                <TableBody>
                  {envios
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((row) => {

                      const { id, descripcion, codigoDeSeguimiento, dnidestinatario, estado, createdAt } = row;

                      return (
                        <TableRow
                          hover
                          key={id}
                          tabIndex={-1}
                        >
                          <TableCell>
                            <Stack direction="row" alignItems="center" spacing={2}>
                              <Typography variant="subtitle2">
                                {codigoDeSeguimiento}
                              </Typography>
                            </Stack>
                          </TableCell>
                          <TableCell align="left">{fDateTime(createdAt)}</TableCell>
                          <TableCell align="left">{descripcion}</TableCell>
                          <TableCell align="left">{dnidestinatario}</TableCell>
                          <TableCell align="left">
                            <Label
                              variant="ghost"
                              color="success"
                            >
                              {estado}
                            </Label>
                          </TableCell>
                          <TableCell align="right">
                            <EnvioMoreMenu codigoDeSeguimiento={codigoDeSeguimiento} />
                          </TableCell>
                        </TableRow>
                      );
                    })}
                </TableBody>
              </Table>
            </TableContainer>
          </Scrollbar>

          <TablePagination
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={envios.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </Card>
      </Container>
    </Page>
  );
}

export default Envios;