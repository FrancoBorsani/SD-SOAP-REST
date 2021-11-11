import Router from './routes/routes';
import ThemeConfig from './theme';
import GlobalStyles from './theme/globalStyles';
import ScrollToTop from './components/ScrollToTop';
import { DataProvider } from './store/GlobalState';

export default function App() {
  return (
    <DataProvider>
      <ThemeConfig>
        <ScrollToTop />
        <GlobalStyles />
        <Router />
      </ThemeConfig>
    </DataProvider>
  );
}
