import PropTypes from 'prop-types';
import { Paper, Typography } from '@mui/material';

SearchNotFound.propTypes = {
  searchQuery: PropTypes.string
};

export default function SearchNotFound({ searchQuery = '', ...other }) {
  return (
    <Paper {...other}>
      <Typography gutterBottom align="center" variant="subtitle1">
        No Encontrado.
      </Typography>
      <Typography variant="body2" align="center">
        No se encontraron resultados para &nbsp;
        <strong>&quot;{searchQuery}&quot;</strong>. Intente comprobar si hay errores tipográficos o utilizar palabras completas.
      </Typography>
    </Paper>
  );
}
