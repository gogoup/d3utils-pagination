package org.gogoup.dddutils.pagination;

public abstract class AutoPaginatedResultDelegate<T> implements PaginatedResultDelegate<T> {
    
    private int argumentIndex;
    private String[] tags;
    
    public AutoPaginatedResultDelegate(int argumentIndex, String ... tags) {
        this.argumentIndex= argumentIndex;
        this.tags = tags;
    }

    @Override
    public boolean isFetchAllResultsSupported(String tag, Object[] arguments) {
        checkForTags(tag);
        PaginatedResult<?> osResult = (PaginatedResult<?>) arguments[argumentIndex];
        return osResult.isGetAllResultsSupported();
    }

    @Override
    public Object getNextPageCursor(String tag, Object[] arguments,
            Object pageCursor, T result) {
        checkForTags(tag);
        PaginatedResult<?> osResult = (PaginatedResult<?>) arguments[argumentIndex];
        return osResult.getNextPageCursor();
    }

    @Override
    public Object getPrevPageCursor(String tag, Object[] arguments,
            Object pageCursor, T result) {
        checkForTags(tag);
        PaginatedResult<?> osResult = (PaginatedResult<?>) arguments[argumentIndex];
        return osResult.getPrevPageCursor();
    }

    @Override
    public Object getFirstPageCursor(String tag, Object[] arguments) {
        checkForTags(tag);
        PaginatedResult<?> osResult = (PaginatedResult<?>) arguments[argumentIndex];
        return osResult.getFirstPageCursor();
    }
    
    private void checkForTags(String tag) {
        for (String t: tags) {
            if (t.equals(tag)) {
                return;
            }
        }
        throw new UnsupportedOperationException(tag);
    }

}
