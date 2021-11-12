import { Icon } from '@iconify/react';
import UserIcon from '@iconify/icons-ant-design/user';
import { alpha, styled } from '@mui/material/styles';
import { Card, Typography } from '@mui/material';
import { useContext } from 'react';
import { DataContext } from 'src/store/GlobalState';

const RootStyle = styled(Card)(({ theme }) => ({
  textAlign: 'center',
  padding: theme.spacing(8, 2, 8, 2),
  color: theme.palette.primary.darker,
}));

const IconWrapperStyle = styled('div')(({ theme }) => ({
  margin: 'auto',
  display: 'flex',
  borderRadius: '50%',
  alignItems: 'center',
  width: theme.spacing(10),
  height: theme.spacing(10),
  justifyContent: 'center',
  marginBottom: theme.spacing(3),
  color: theme.palette.primary.dark,
  backgroundImage: `linear-gradient(135deg, ${alpha(theme.palette.primary.dark, 0)} 0%, ${alpha(
    theme.palette.primary.dark,
    0.24
  )} 100%)`
}));


const ProfileCard = () => {

  const { state } = useContext(DataContext);

  const { auth } = state;

  return (
    <RootStyle>
      <IconWrapperStyle>
        <Icon icon={UserIcon} width={34} height={34} />
      </IconWrapperStyle>
      <Typography variant="h3">{auth.username}</Typography>
      <Typography variant="subtitle" sx={{ opacity: 0.72 }}>
        {auth.email}
      </Typography>
      <Typography variant="subtitle2" sx={{ opacity: 0.72 }}>
        {auth.nombre + " " + auth.apellido}
      </Typography>
      <Typography variant="subtitle2" sx={{ opacity: 0.72 }}>
        {auth.documento}
      </Typography>
    </RootStyle>
  );
}

export default ProfileCard;
