import { Navigate, useRoutes } from 'react-router-dom';
import DashboardLayout from '../layouts/dashboard';
import LogoOnlyLayout from '../layouts/LogoOnlyLayout';
import LoginPage from '../pages/LoginPage';
import DashboardApp from '../pages/DashboardPage';
import Envios from '../pages/Envios';
import NotFound from '../pages/Page404';
import EnvioDetailPage from '../pages/EnvioDetailPage';
import ProfilePage from '../pages/ProfilePage';
import SearchPage from '../pages/SearchPage';
import { useContext } from 'react';
import { DataContext } from '../store/GlobalState';


export default function Router() {

  const { state } = useContext(DataContext);

  const { auth } = state;

  return useRoutes([
    {
      path: '/dashboard',
      element: auth.username ?  <DashboardLayout /> : <Navigate to="/login" />,
      children: [
        { element: <Navigate to="/dashboard/app" replace /> },
        { path: 'app', element: <DashboardApp /> },
        { path: 'envios', element: <Envios /> },
        { path: 'envios/:id', element: <EnvioDetailPage /> },
        { path: 'search', element: <SearchPage /> },
        { path: 'profile', element: <ProfilePage /> }
      ]
    },
    {
      path: '/',
      element: <LogoOnlyLayout />,
      children: [
        { path: 'login', element: <LoginPage /> },
        { path: '404', element: <NotFound /> },
        { path: '/', element: <Navigate to="/dashboard" /> },
        { path: '*', element: <Navigate to="/404" /> }
      ]
    },
    { path: '*', element: <Navigate to="/404" replace /> }
  ]);
}
