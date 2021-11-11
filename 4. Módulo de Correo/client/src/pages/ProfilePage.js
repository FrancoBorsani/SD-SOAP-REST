import ProfileCard from "src/components/ProfileCard";
import { Grid } from '@mui/material';
import Page from "src/components/Page";

const Profile = () => {
    return (
        <Page title="Perfil | Módulo-correo">
            <Grid container spacing={3} justifyContent="center">
                <Grid item xs={12} sm={6} md={4} m={1}>
                    <ProfileCard/>
                </Grid>
            </Grid>
        </Page>
    )
}

export default Profile;