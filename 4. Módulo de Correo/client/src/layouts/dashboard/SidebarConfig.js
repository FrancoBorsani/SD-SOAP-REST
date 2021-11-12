import { Icon } from '@iconify/react';
import pieChart2Fill from '@iconify/icons-eva/pie-chart-2-fill';
import shippingFill from '@iconify/icons-ic/round-local-shipping';
import userFill from '@iconify/icons-ic/account-circle';

const getIcon = (name) => <Icon icon={name} width={22} height={22} />;

const sidebarConfig = [
  {
    title: 'dashboard',
    path: '/dashboard/app',
    icon: getIcon(pieChart2Fill)
  },
  {
    title: 'envios',
    path: '/dashboard/envios',
    icon: getIcon(shippingFill)
  },
  {
    title: 'Perfil',
    path: '/dashboard/profile',
    icon: getIcon(userFill)
  }
];

export default sidebarConfig;
