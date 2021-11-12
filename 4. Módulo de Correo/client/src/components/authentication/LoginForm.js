import * as Yup from 'yup';
import { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useFormik, Form, FormikProvider } from 'formik';
import { Icon } from '@iconify/react';
import eyeFill from '@iconify/icons-eva/eye-fill';
import eyeOffFill from '@iconify/icons-eva/eye-off-fill';
import {
  Stack,
  TextField,
  IconButton,
  InputAdornment,
  Alert
} from '@mui/material';
import { LoadingButton } from '@mui/lab';
import { postData } from 'src/utils/fetchData';
import { DataContext } from 'src/store/GlobalState';


const LoginForm = () => {

  const { dispatch } = useContext(DataContext);

  const navigate = useNavigate();
  const [showPassword, setShowPassword] = useState(false);

  const [error, setError] = useState('')

  const LoginSchema = Yup.object().shape({
    username: Yup.string().required('Por favor ingrese su nombre de usuario.'),
    password: Yup.string().required('Por favor ingrese su contraseña.')
  });

  const formik = useFormik({
    initialValues: {
      username: '',
      password: '',
    },
    validationSchema: LoginSchema,
    onSubmit: async () => {

      const response = await postData('auth/signin', values);

      if (response.error) return setError('Usuario o contraseña incorrectos.');

      dispatch({
        type: 'AUTH', payload: 
          response
      });  
    
      localStorage.setItem('jwt', response.jwt);

      navigate('/dashboard', { replace: true });
    }
  });

  const { errors, touched, values, isSubmitting, handleSubmit, getFieldProps } = formik;

  const handleShowPassword = () => {
    setShowPassword((show) => !show);
  };

  return (
    <FormikProvider value={formik}>
      <Form autoComplete="off" noValidate onSubmit={handleSubmit}>
        <Stack spacing={3} sx={{ my: 2 }}>

          {
            error && (
              <Alert severity="error">{ error }</Alert>
            )
          }

          <TextField
            fullWidth
            autoComplete="username"
            type="text"
            label="Username"
            {...getFieldProps('username')}
            error={Boolean(touched.username && errors.username)}
            helperText={touched.username && errors.username}
          />

          <TextField
            fullWidth
            autoComplete="current-password"
            type={showPassword ? 'text' : 'password'}
            label="Password"
            {...getFieldProps('password')}
            InputProps={{
              endAdornment: (
                <InputAdornment position="end">
                  <IconButton onClick={handleShowPassword} edge="end">
                    <Icon icon={showPassword ? eyeFill : eyeOffFill} />
                  </IconButton>
                </InputAdornment>
              )
            }}
            error={Boolean(touched.password && errors.password)}
            helperText={touched.password && errors.password}
          />
        </Stack>

        <LoadingButton
          fullWidth
          size="large"
          type="submit"
          variant="contained"
          loading={isSubmitting}
        >
          Login
        </LoadingButton>
      </Form>
    </FormikProvider>
  );
}

export default LoginForm;
